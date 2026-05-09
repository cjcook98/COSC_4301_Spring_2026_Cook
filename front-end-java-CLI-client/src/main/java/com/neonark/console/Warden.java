package com.neonark.console;

public class Warden {
    private String id;
    private String firstName;
    private String lastName;
    private String status;
    private String role;

    public Warden(String id, String firstName, String lastName, String status, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.role = role;
    }

    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getStatus() { return status; }
    public String getRole() { return role; }
}