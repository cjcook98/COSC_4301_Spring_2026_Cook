package com.neonark.console;

import java.util.Scanner;

public class MainMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final WardenService wardenService = new WardenService();

    public void start() {
        boolean running = true;

        while (running) {
            printMainMenu(); // Print once per full cycle

            boolean validChoice = false;

            while (!validChoice) {

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        validChoice = true;
                        wardenService.addNewWarden();
                        break;

                    case "2":
                        validChoice = true;
                        wardenService.viewAllWardens();
                        break;

                    case "3":
                        validChoice = true;
                        simulatedUpdateWarden();
                        break;

                    case "4":
                        validChoice = true;
                        simulatedManageCertifications();
                        break;

                    case "5":
                        validChoice = true;
                        simulatedDeactivateWarden();
                        break;

                    case "6":
                        validChoice = true;
                        System.out.println("Exiting Neon Ark Console...");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid selection. Please try again.");
                        System.out.print("Select an option (1-6): ");
                }
            }
        }
    }

    private void printMainMenu() {
        System.out.println("=========================================================");
        System.out.println("        NEON ARK — ADMIN WARDEN ONBOARDING CONSOLE");
        System.out.println("=========================================================");
        System.out.println("[ MAIN MENU ]");
        System.out.println("---------------------------------------------------------");
        System.out.println("1. Add New Warden");
        System.out.println("2. View Wardens");
        System.out.println("3. Update Warden");
        System.out.println("4. Manage Certifications");
        System.out.println("5. Deactivate / Terminate Warden");
        System.out.println("6. Exit");
        System.out.print("Select an option: ");
    }

    private void simulatedUpdateWarden() {
        System.out.println("\n[SIMULATED ACTION] Update Warden");
        System.out.println("Inputs required: wardenId, fields to update");
        System.out.println("WOULD SEND: PUT /api/wardens/{id}");
        System.out.println("Result: SUCCESS (simulated)\n");
    }

    private void simulatedManageCertifications() {
        System.out.println("\n[SIMULATED ACTION] Manage Certifications");
        System.out.println("Inputs required: wardenId, certification details");
        System.out.println("WOULD SEND: POST /api/wardens/{id}/certifications");
        System.out.println("Result: SUCCESS (simulated)\n");
    }

    private void simulatedDeactivateWarden() {
        System.out.println("\n[SIMULATED ACTION] Deactivate / Terminate Warden");
        System.out.println("Inputs required: wardenId, termination reason");
        System.out.println("WOULD SEND: PUT /api/wardens/{id}/status");
        System.out.println("Result: SUCCESS (simulated)\n");
    }
}