package com.neonark.cli.actions;

import com.neonark.cli.HttpClientHelper;
import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.util.Scanner;

public class RenameCreatureAction {

    private final Scanner scanner;

    public RenameCreatureAction(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {

        System.out.print("Enter creature ID: ");
        String idInput = scanner.nextLine().trim();

        if (!idInput.matches("\\d+")) {
            System.out.println("Invalid ID. Must be numeric.");
            return;
        }

        long id = Long.parseLong(idInput);

        System.out.print("Enter new creature name: ");
        String newName = scanner.nextLine().trim();

        if (newName.isBlank()) {
            System.out.println("Name cannot be blank.");
            return;
        }

        // Build JSON body
        JSONObject body = new JSONObject();
        body.put("newName", newName);

        try {
            HttpResponse<String> response =
                    HttpClientHelper.put("/api/creatures/" + id + "/rename", body.toString());

            int code = response.statusCode();

            if (code == 200) {
                JSONObject c = new JSONObject(response.body());
                System.out.println("\nCreature renamed successfully!");
                System.out.println("ID: " + c.getInt("id"));
                System.out.println("Old Name: " + c.getString("oldName"));
                System.out.println("New Name: " + c.getString("newName"));
                return;
            }

            if (code == 404) {
                System.out.println("Creature not found.");
                return;
            }

            if (code == 400 || code == 409) {
                JSONObject err = new JSONObject(response.body());
                System.out.println("Error: " + err.getString("error"));
                return;
            }

            System.out.println("Unexpected error: " + response.body());
        } catch (Exception e) {
            System.out.println("Failed to rename creature: " + e.getMessage());
        }
    }
}
