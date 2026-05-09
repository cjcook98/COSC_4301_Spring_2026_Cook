package com.neonark.console;

public class Validation {

    public static boolean isValidDate(String input) {
        return input.matches("\\d{4}-\\d{2}-\\d{2}"); // YYYY-MM-DD
    }

    public static boolean isRequired(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidStatus(String input) {
         return input.equals("Active") ||
                input.equals("OnLeave") ||
                input.equals("Terminated");
    }

    public static boolean isValidRole(String input) {
         return input.equals("Admin") ||
                input.equals("Field") ||
                input.equals("Rift") ||
                input.equals("Trainer") ||
                input.equals("Astral");
    }
}