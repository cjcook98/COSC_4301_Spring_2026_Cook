package com.neonark.cli.actions;

import com.neonark.cli.HttpClientHelper;
import com.neonark.cli.TableFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ListCreaturesAction {

    public void run() {
        try {
            var response = HttpClientHelper.get("/api/creatures");

            if (response.statusCode() != 200) {
                System.out.println("Error: " + response.body());
                return;
            }

            JSONArray arr = new JSONArray(response.body());

            if (arr.isEmpty()) {
                System.out.println("No creatures found.");
                return;
            }

            TableFormatter.printRow(List.of("ID", "Name", "Status", "Habitat"));
            TableFormatter.printSeparator(4);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject c = arr.getJSONObject(i);

                TableFormatter.printRow(List.of(
                        String.valueOf(c.getInt("id")),
                        c.getString("name"),
                        c.getString("status"),
                        c.getString("habitatName")
                ));
            }
        } catch (Exception e) {
            System.out.println("Failed to fetch creatures: " + e.getMessage());
        }
    }
}
