package com.neonark.cli.actions;

import com.neonark.cli.HttpClientHelper;
import com.neonark.cli.TableFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class ViewObservationsAction {

    private final Scanner scanner;

    public ViewObservationsAction(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {

        System.out.print("Enter creature ID: ");
        String input = scanner.nextLine().trim();

        if (!input.matches("\\d+")) {
            System.out.println("Invalid ID. Please enter a numeric value.");
            return;
        }

        long id = Long.parseLong(input);

        try {
            HttpResponse<String> response =
                    HttpClientHelper.get("/api/creatures/" + id + "/observations");

            int code = response.statusCode();

            // If error code NOT_FOUND is returned for the creature.
            if (code == 404) {
                System.out.println("Creature not found.");
                return;
            }

            // If any other error other than NOT_FOUND or OK is returned.
            if (code != 200) {
                System.out.println("Unexpected error: " + response.body());
                return;
            }

            JSONObject c = new JSONObject(response.body());

            // Formatting the table
            System.out.println("\nCREATURE DETAILS");
            TableFormatter.printSeparator(2);

            TableFormatter.printRow(List.of("Field", "Value"));
            TableFormatter.printSeparator(2);

            TableFormatter.printRow(List.of("ID", String.valueOf(c.getInt("id"))));
            TableFormatter.printRow(List.of("Name", c.getString("name")));
            TableFormatter.printRow(List.of("Status", c.getString("status")));
            TableFormatter.printRow(List.of("Habitat", c.getString("habitatName")));

            System.out.println("\nOBSERVATIONS");
            TableFormatter.printSeparator(3);

            JSONArray obs = c.getJSONArray("observations");

            if (obs.isEmpty()) {
                System.out.println("No observations found.");
                return;
            }

            TableFormatter.printRow(List.of(
                    String.format("%-30s", "Timestamp"),
                    "Note"));
            TableFormatter.printSeparator(3);

            for (int i = 0; i < obs.length(); i++) {
                JSONObject o = obs.getJSONObject(i);
                TableFormatter.printRow(List.of(
                        String.format("%-30s", o.getString("createdAt")),
                        o.getString("note")
                ));
            }
        } catch (Exception e) {
            System.out.println("Failed to fetch creature: " + e.getMessage());
        }
    }
}
