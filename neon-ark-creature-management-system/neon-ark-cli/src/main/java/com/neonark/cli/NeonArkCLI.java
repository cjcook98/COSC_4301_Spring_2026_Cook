package com.neonark.cli;

import com.neonark.cli.actions.*;
import java.util.Scanner;

public class NeonArkCLI {

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean running = true;

        while (running) {
            MenuRenderer.showMainMenu(); // Prints once per cycle

            boolean validChoice = false;

            while (!validChoice) {
                System.out.print("Select an option: ");
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    // Lists all creatures
                    case "1" -> {
                        validChoice = true;
                        new ListCreaturesAction().run();
                    }

                    // Views a specific creature with a creature ID
                    case "2" -> {
                        validChoice = true;
                        new ViewCreatureAction(scanner).run();
                    }

                    // Registers a creature using name, habitat id, and status
                    case "3" -> {
                        validChoice = true;
                        new RegisterCreatureAction(scanner).run();
                    }

                    // Renames a creature with a given creature id
                    case "4" -> {
                        validChoice = true;
                        new RenameCreatureAction(scanner).run();
                    }

                    // Removes a creature with a given creature id
                    case "5" -> {
                        validChoice = true;
                        new RemoveCreatureAction(scanner).run();
                    }

                    // Views the observations of a creature with a given creature id
                    case "6" -> {
                        validChoice = true;
                        new ViewObservationsAction(scanner).run();
                    }

                    // Finds all creatures with feeding times at the given time (HH:MM)
                    case "7" -> {
                        validChoice = true;
                        new FindFeedingsAction(scanner).run();
                    }

                    // Views all users
                    case "8" -> {
                        validChoice = true;
                        new ViewUsersAction().run();
                    }

                    // Exit
                    case "0" -> {
                        validChoice = true;
                        if (confirmExit()) {
                            running = false;
                        }
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }

            System.out.println();
        }
    }

    private boolean confirmExit() {
        while (true) {
            System.out.print("Are you sure you want to exit? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("Invalid option. Please enter 'y' or 'n'.");
            }
        }
    }


    public static void main (String[] args) {
        new NeonArkCLI().start();
    }
}