package com.neonark.cli.actions;

import com.neonark.cli.HttpClientHelper;
import com.neonark.cli.TableFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.util.List;

public class ViewUsersAction {

    public void run() {

        try {
            HttpResponse<String> response =
                    HttpClientHelper.get("/api/admin/users");

            int code = response.statusCode();

            if (code != 200) {
                System.out.println("Unexpected error: " + response.body());
                return;
            }

            JSONArray arr = new JSONArray(response.body());

            System.out.println("\nREGISTERED USERS");
            TableFormatter.printSeparator(5);

            TableFormatter.printRow(List.of(
                    "ID",
                    String.format("%-20s", "Full Name"),
                    String.format("%-30s", "Email"),
                    String.format("%-15s", "Phone"),
                    String.format("%-10s", "Role")
            ));
            TableFormatter.printSeparator(5);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject u = arr.getJSONObject(i);
                TableFormatter.printRow(List.of(
                        String.valueOf(u.getInt("id")),
                        String.format("%-20s", u.getString("fullName")),
                        String.format("%-30s", u.getString("email")),
                        String.format("%-15s", u.getString("phone")),
                        String.format("%-10s", u.getString("role"))
                ));
            }
        } catch (Exception e) {
            System.out.println("Failed to fetch users: " + e.getMessage());
        }
    }
}