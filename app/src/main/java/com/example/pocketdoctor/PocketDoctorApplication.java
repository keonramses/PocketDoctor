package com.example.pocketdoctor;

import android.app.Application;

import java.io.Serializable;

public class PocketDoctorApplication extends Application implements Serializable {
    private String currentUserId = "";
    private int userType = 0;
    private String name;
    private String lastName;
    private String email;
    private String msp;
    private String address;


    public String getCurrentUserId() {
        return currentUserId;
    }
    public String getCurrentUserName() {
        return name;
    }
    public String getCurrentUserLastName() {
        return lastName;
    }
    public String getCurrentUserEmail() {
        return email;
    }
    public String getCurrentMSP(){return msp;}
    public String getCurrentAddress(){return address;}
    public int getCurrentUserType() {return userType;}


    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }
    public void setCurrentUserName(String name) {
        this.name = name;
    }
    public void setCurrentUserLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setCurrentUserEmail(String email) {
        this.email = email;
    }
    public void setCurrentUserMSP(String msp) {
        this.msp = msp;
    }
    public void setCurrentUserAddress(String address) {this.address = address;}


    public void setCurrentUserType(int userType){this.userType = userType; }
}
