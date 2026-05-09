package com.neonark.console;

import java.util.List;

public class ConsoleUtils {

    public static void printWardenTable(List<Warden> wardens) {
        System.out.println("\n---------------------------------------------------------");
        System.out.printf("%-10s %-15s %-15s %-12s %-15s%n",
                "ID", "First Name", "Last Name", "Status", "Role");
        System.out.println("---------------------------------------------------------");

        for (Warden w : wardens) {
            System.out.printf("%-10s %-15s %-15s %-12s %-15s%n",
                    w.getId(), w.getFirstName(), w.getLastName(), w.getStatus(), w.getRole());
        }

        System.out.println("---------------------------------------------------------\n");
    }
}