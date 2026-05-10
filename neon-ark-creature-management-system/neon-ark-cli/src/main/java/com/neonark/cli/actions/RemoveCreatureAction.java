package com.neonark.cli.actions;

import com.neonark.cli.HttpClientHelper;
import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.util.Scanner;

public class RemoveCreatureAction {

    private final Scanner scanner;

    public RemoveCreatureAction(Scanner scanner) {
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

        try {
            HttpResponse<String> response =
                    HttpClientHelper.delete("/api/creatures/" + id);

            int code = response.statusCode();

            if (code == 200) {
                JSONObject json = new JSONObject(response.body());
                System.out.println("\nCreature removed successfully!");
                System.out.println("ID: " + json.getInt("id"));
                System.out.println("Message: " + json.getString("message"));
                System.out.println("Status: " + json.getString("status"));
                return;
            }

            if (code == 404) {
                System.out.println("Creature not found.");
                return;
            }

            if (code == 409) {
                JSONObject err = new JSONObject(response.body());
                System.out.println("Error: " + err.getString("error"));
                return;
            }

            System.out.println("Unexpected error: " + response.body());
        } catch (Exception e) {
            System.out.println("Failed to remove creature: " + e.getMessage());
        }
    }
}
