package com.neonark.cli;

public class MenuRenderer {

    public static void showMainMenu() {
        System.out.println("=====================================");
        System.out.println("      NEON ARK CLI SYSTEM");
        System.out.println("=====================================");
        System.out.println("1. List all creatures");
        System.out.println("2. View creature by ID");
        System.out.println("3. Register new creature");
        System.out.println("4. Rename creature");
        System.out.println("5. Remove creature");
        System.out.println("6. View creature observations/notes");
        System.out.println("7. Find creatures by feeding time");
        System.out.println("--- Admin Only ---");
        System.out.println("8. View all system users");
        System.out.println("0. Exit");
        System.out.println("-------------------------------------");
    }
}
