package com.neonark.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {

    public List<Warden> loadWardens() {
        List<Warden> wardens = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream("/wardens.csv")
                )
        )) {
            String line;
            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Warden w = new Warden(
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3],
                        parts[4]
                );
                wardens.add(w);
            }
        } catch (Exception e) {
            System.out.println("Error loading CSV: " + e.getMessage());
        }

        return wardens;
    }
}