package com.neonark.cli.actions;

import com.neonark.cli.HttpClientHelper;
import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.util.Scanner;

public class RegisterCreatureAction {

    private final Scanner scanner;

    public RegisterCreatureAction(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {

        System.out.print("Enter creature name: ");
        String name = scanner.nextLine().trim();

        if (name.isBlank()) {
            System.out.println("Name cannot be blank.");
            return;
        }

        System.out.print("Enter habitat ID: " );
        String habitatInput = scanner.nextLine().trim();

        if (!habitatInput.matches("\\d+")) {
            System.out.println("Invalid habitat ID. Must be numeric.");
            return;
        }

        long habitatId = Long.parseLong(habitatInput);

        System.out.print("Enter status (Healthy, Injured, Critical, Removed): ");
        String status = scanner.nextLine().trim();

        if (status.isBlank()) {
            System.out.println("Status cannot be blank.");
            return;
        }

        // Build JSON body
        JSONObject body = new JSONObject();
        body.put("name", name);
        body.put("habitatId", habitatId);
        body.put("status", status);

        try {
            HttpResponse<String> response =
                    HttpClientHelper.post("/api/creatures", body.toString());

            int code = response.statusCode();

            if (code == 200 || code == 201) {
                JSONObject c = new JSONObject(response.body());
                System.out.println("\nCreature registered successfully!");
                System.out.println("ID: " + c.getInt("id"));
                System.out.println("Name: " + c.getString("name"));
                System.out.println("Status: " + c.getString("status"));
                System.out.println("Habitat: " + c.getString("habitatName"));
                return;
            }

            // Handle backend validation errors
            if (code == 400 || code == 409) {
                JSONObject err = new JSONObject(response.body());
                System.out.println("Error: " + err.getString("error"));
                return;
            }

            // Unexpected error
            System.out.println("Unexpected error: " + response.body());
        } catch (Exception e) {
            System.out.println("Failed to register creature: " + e.getMessage());
        }
    }
}
