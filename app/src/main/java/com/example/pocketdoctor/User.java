package com.example.pocketdoctor;

public class User {
    String UserId;
    String firstName, lastName, address;
    String duePaymentMessage;

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

    public User() {
    }

    public void setUserFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setUserLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserDuePaymentMessage(String duePaymentMessage) {
        this.duePaymentMessage = duePaymentMessage;
    }

    public String getDuePaymentMessage() {
        return duePaymentMessage;
    }

    public String getUserId() {
        return UserId;
    }

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
