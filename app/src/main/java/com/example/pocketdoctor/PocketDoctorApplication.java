package com.example.pocketdoctor;

import android.app.Application;

public class PocketDoctorApplication extends Application {
    private String currentUserId = "";

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }
}
