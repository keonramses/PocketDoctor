package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    TextView name, lastName, email, Msp, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


    }



    public void gotoEditUser(View view){
       startActivity(new Intent(UserProfile.this, EditAdminUserAccountActivity.class));
    }
    public void goToLogin(View view){
        startActivity(new Intent(UserProfile.this, LoginActivity.class));
    }
    public void goToFoodTracker(View view){
        startActivity(new Intent(UserProfile.this, FoodTrackerActivity.class));
    }
}