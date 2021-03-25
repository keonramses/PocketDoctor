package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pocketdoctor.repository.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends AppCompatActivity {

    TextView name, lastName, email, msp, status;
    DatabaseHelper databaseHelper;
    String userId, sName, sLastName, sEmail, sMsp, sStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        userId = ((PocketDoctorApplication)getApplication()).getCurrentUserId();
        name = findViewById(R.id.txtViewUserName);
        lastName = findViewById(R.id.txtViewUserLastName);
        email = findViewById(R.id.txtViewUserEmail);
        msp = findViewById(R.id.txtViewUserMsp);
        databaseHelper = new DatabaseHelper(this);
        // TODO -->
        status = findViewById(R.id.txtViewStatus);
        getUserProfile();
    }

    public void getUserProfile(){
        Cursor c = databaseHelper.getUser(userId);
        if(c.getCount()>0){
            while (c.moveToNext()){
                sName = c.getString(0);
                sLastName = c.getString(1);
                sEmail = c.getString(2);
                sMsp = c.getString(3);
                if(sMsp.equalsIgnoreCase("no")){
                    //sStatus =
                }
            }
            name.setText(sName);
            lastName.setText(sLastName) ;
            email.setText(sEmail) ;
            msp.setText(sMsp) ;
        }
    }

    public void gotoEditUser(View view){
      // TODO go to Edit USer
     //  startActivity(new Intent(UserProfile.this, .class));
    }
    public void goToLogin(View view){
        startActivity(new Intent(UserProfile.this, LoginActivity.class));
    }
    public void goToFoodTracker(View view){
        startActivity(new Intent(UserProfile.this, FoodTrackerActivity.class));
    }
}