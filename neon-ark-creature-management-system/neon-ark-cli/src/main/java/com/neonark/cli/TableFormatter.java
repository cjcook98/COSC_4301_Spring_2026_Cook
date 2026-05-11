package com.neonark.cli;

import java.util.List;

public class TableFormatter {

    // Prints a row with static padding for the columns.
    public static void printRow(List<String> columns) {
        for (String col : columns) {
            System.out.printf("%-20s", col);
        }
        System.out.println();
    }

    // Uses "-" as the separator, using 20 for each column.
    public static void printSeparator(int columns) {
        System.out.println("-".repeat(columns * 20));
    }
}
