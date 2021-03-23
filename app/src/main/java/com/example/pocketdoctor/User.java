package com.example.pocketdoctor;

public class User {
    String UserId;
    String firstName, lastName, address;

    public User(String userId, String firstName, String lastName, String address) {
        UserId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public User(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public User() { }

    public String getUserId() { return UserId; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }
}
