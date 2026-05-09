package com.neonark.console;

import java.awt.desktop.SystemEventListener;
import java.util.List;
import java.util.Scanner;

public class WardenService {

    private final CsvLoader loader = new CsvLoader();
    private final List<Warden> wardens = loader.loadWardens();

    private boolean isIdentityUnique(String firstName, String lastName) {
        boolean isUnique = true;

        for (Warden w : wardens) {
            boolean sameFirst = w.getFirstName().equalsIgnoreCase(firstName);
            boolean sameLast = (lastName == null && (w.getLastName() == null || w.getLastName().isEmpty()))
                    || (lastName != null && lastName.equalsIgnoreCase(w.getLastName()));

            if (sameFirst && sameLast) {
                isUnique = false;
                break;
            }
        }
        return isUnique;
    }

    public void viewAllWardens() {
        ConsoleUtils.printWardenTable(wardens);
    }

    public void addNewWarden() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=========================================================");
        System.out.println("                   ADD NEW WARDEN (SIMULATED)");
        System.out.println("=========================================================");

        // FIRST NAME (required)
        String firstName;
        while (true) {
            System.out.print("Enter first name (required): ");
            firstName = scanner.nextLine().trim();

            if (!Validation.isRequired(firstName)) {
                System.out.println("ERROR: First name cannot be blank.");
                continue;
            }
            break;
        }

        // LAST NAME (optional)
        System.out.print("Enter last name (optional): ");
        String lastName = scanner.nextLine().trim();
        if (lastName.isEmpty()) lastName = null;

        // STATUS (required, controlled)
        String status;
        while (true) {
            System.out.print("Enter status [Active | OnLeave | Terminated]: ");
            status = scanner.nextLine().trim();

            if (!Validation.isValidStatus(status)) {
                System.out.println("ERROR: Invalid status. Must be Active, OnLeave, or Terminated.");
                continue;
            }
            break;
        }

        // ROLE (required, controlled)
        String role;
        while (true) {
            System.out.print("Enter role [Admin | Field | Rift | Trainer | Astral]: ");
            role = scanner.nextLine().trim();

            if (!Validation.isValidRole(role)) {
                System.out.println("ERROR: Invalid role. Must be Admin, Field, Rift, Trainer, or Astral.");
                continue;
            }
            break;
        }

        // UNIQUENESS CHECK
        if (!isIdentityUnique(firstName, lastName)) {
            System.out.println("\nERROR: A warden with this identity already exists in the system.");
            System.out.println("Identity must be unique according to Neon Ark database rules.");
            return;
        }

        // SIMULATED NEW ID
        int newId = wardens.stream()
                .mapToInt(w -> Integer.parseInt(w.getId()))
                .max()
                .orElse(0) + 1;

        // PRINT SIMULATED OUTBOUND REQUEST
        System.out.println("\n---------------------------------------------------------");
        System.out.println("WOULD SEND:  POST  /api/wardens");
        System.out.println("BRIEF DESCRIPTION: Create a new Warden record in the Neon Ark system.");
        System.out.println("PAYLOAD (JSON)");
        System.out.println("{");
        System.out.println("  \"wardenId\": \"" + newId + "\",");
        System.out.println("  \"firstName\": \"" + firstName + "\",");
        System.out.println("  \"lastName\": " + (lastName == null ? "null" : "\"" + lastName + "\"") + ",");
        System.out.println("  \"status\": \"" + status + "\",");
        System.out.println("  \"role\": \"" + role + "\"");
        System.out.println("}");
        System.out.println("---------------------------------------------------------");

        // SIMULATED RESULT
        System.out.println("RESULT: SUCCESS - Warden creation simulated.");
        System.out.println("NOTE: No data was written to the CSV. This is a boundary simulation.\n");
    }
}