package com.neonark.cli;

import java.util.List;

public class TableFormatter {

    public static void printRow(List<String> columns) {
        for (String col : columns) {
            System.out.printf("%-20s", col);
        }
        System.out.println();
    }

    public static void printSeparator(int columns) {
        System.out.println("-".repeat(columns * 20));
    }
}
