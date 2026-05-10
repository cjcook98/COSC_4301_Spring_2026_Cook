package com.neonark.cli.actions;

import com.neonark.cli.HttpClientHelper;
import com.neonark.cli.TableFormatter;
import org.json.JSONObject;

import javax.swing.text.View;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class ViewCreatureAction {

    private final Scanner scanner;

    public ViewCreatureAction(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.print("Enter creature ID: ");
        String input = scanner.nextLine().trim();

        // Validate numeric input
        if (!input.matches("\\d+")) {
            System.out.println("Invalid ID. Please enter a numeric value.");
            return;
        }

        long id = Long.parseLong(input);

        try {
            HttpResponse<String> response = HttpClientHelper.get("/api/creatures/" + id);

            if (response.statusCode() == 404) {
                System.out.println("Creature not found.");
                return;
            }

            if (response.statusCode() != 200) {
                System.out.println("Error: " + response.body());
                return;
            }

            JSONObject c = new JSONObject(response.body());

            System.out.println("\nCREATURE DETAILS");
            TableFormatter.printSeparator(2);

            TableFormatter.printRow(List.of("Field", "Value"));
            TableFormatter.printSeparator(2);

            TableFormatter.printRow(List.of("ID", String.valueOf(c.getInt("id"))));
            TableFormatter.printRow(List.of("Name", c.getString("name")));
            TableFormatter.printRow(List.of("Status", c.getString("status")));
            TableFormatter.printRow(List.of("Habitat", c.getString("habitatName")));
        } catch (Exception e) {
            System.out.println("Failed to fetch creature: " + e.getMessage());
        }
    }
}
