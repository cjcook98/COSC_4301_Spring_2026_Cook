package com.neonark.cli.actions;

import com.neonark.cli.HttpClientHelper;
import com.neonark.cli.TableFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class FindFeedingsAction {

    private final Scanner scanner;

    public FindFeedingsAction(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.print("Enter feeding time (HH:MM): ");
        String time = scanner.nextLine().trim();

        // Basic format check
        if (!time.matches("^\\d{2}:\\d{2}$")) {
            System.out.println("Invalid time format. Use HH:MM.");
            return;
        }

        try {
            HttpResponse<String> response =
                    HttpClientHelper.get("/api/feedings?time=" + time);

            int code = response.statusCode();

            if (code == 404) {
                System.out.println("No creatures found for this feeding time.");
                return;
            }

            if (code == 400) {
                JSONObject err = new JSONObject(response.body());
                System.out.println("Error: " + err.getString("error"));
                return;
            }

            if (code != 200) {
                System.out.println("Unexpected error: " + response.body());
                return;
            }

            JSONArray arr = new JSONArray(response.body());

            System.out.println("\nCREATURES FEEDING AT " + time);
            TableFormatter.printSeparator(4);

            TableFormatter.printRow(List.of("ID", "Name", "Habitat", "Time"));
            TableFormatter.printSeparator(4);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject c = arr.getJSONObject(i);
                TableFormatter.printRow(List.of(
                        String.valueOf(c.getInt("id")),
                        c.getString("name"),
                        c.getString("habitatName"),
                        c.getString("feedTime")
                ));
            }
        } catch (Exception e) {
            System.out.println("Failed to fetch feeding schedule: " + e.getMessage());
        }
    }
}
