package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pocketdoctor.repository.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends AppCompatActivity {

    TextView name, lastName, email, msp, status, statusLabel;
    DatabaseHelper databaseHelper;
    String userId, sName, sLastName, sEmail, sMsp, sStatus;
    ImageView calorieIcon, homeIcon, stethoscopeIcon;
    Button makePayment, viewCalories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        userId = ((PocketDoctorApplication) getApplication()).getCurrentUserId();
        name = findViewById(R.id.txtViewUserName);
        lastName = findViewById(R.id.txtViewUserLastName);
        email = findViewById(R.id.txtViewUserEmail);
        msp = findViewById(R.id.txtViewUserMsp);
        databaseHelper = new DatabaseHelper(this);
        makePayment = findViewById(R.id.btnMakePayment);
        viewCalories = findViewById(R.id.btnViewCalories);
        statusLabel = findViewById(R.id.txtCreateUserEmailLabel6);

        // TODO -->
        status = findViewById(R.id.txtViewStatus);
        getUserProfile();

        calorieIcon = findViewById(R.id.imageViewFood);
        homeIcon = findViewById(R.id.imageViewHome);
        stethoscopeIcon = findViewById(R.id.imageViewStethoScope);

        //CONTEXTUAL MENU ICON FUNCTION

        viewCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, FoodTrackerActivity.class));
            }
        });
        calorieIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, FoodTrackerActivity.class));
            }
        });
        //CONTEXTUAL MENU ICON FUNCTION
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, UserMain.class));
            }
        });
        //CONTEXTUAL MENU ICON FUNCTION
        stethoscopeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, FindDoctor.class));
            }
        });
    }

    public void getUserProfile() {
        Cursor c = databaseHelper.getUser(userId);
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                sName = c.getString(0);
                sLastName = c.getString(1);
                sEmail = c.getString(2);
                sMsp = c.getString(3);
                if (sMsp.equalsIgnoreCase("no")) {
                    //sStatus =
                    Cursor adate = databaseHelper.getUserDueAppointment(userId);
                    if (adate.getCount() > 0) {
                        while (adate.moveToNext()) {
                            sStatus = adate.getString(0);
                        }
                        status.setText("You have a due payment for " + sStatus);
                    }

                }

            }
            name.setText(sName);
            lastName.setText(sLastName);
            email.setText(sEmail);
            msp.setText(sMsp);
            status.setText("You have a due payment for " + sStatus);

            if ((msp.getText().toString().equalsIgnoreCase("yes"))) {
                makePayment.setVisibility(View.GONE);
                status.setVisibility(View.GONE);
                statusLabel.setVisibility(View.GONE);
            }
        }
    }

    public void updatePayment(View view) {
        boolean paymentUpdated = databaseHelper.updatePayment(userId);
        if (paymentUpdated) {
            makePayment.setText("Payment Successful");
            makePayment.setBackgroundResource(R.drawable.rectangle_round_button_fill);
            makePayment.setTextColor(Color.parseColor("#FFFFFF"));
            Toast.makeText(UserProfile.this, "Payment Successful", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(UserProfile.this, "Payment no Successful", Toast.LENGTH_LONG).show();
        }
    }

        public void gotoEditUser (View view){
            
            PocketDoctorApplication user = new PocketDoctorApplication();
            Intent intent = new Intent(UserProfile.this, EditAdminUserAccountActivity.class);
            user.setCurrentUserId(userId);
            user.setCurrentUserName(sName);
            user.setCurrentUserLastName(sLastName);
            user.setCurrentUserEmail(sEmail);
            user.setCurrentUserMSP(sMsp);
            String comesFromUser = "comes from user";
            intent.putExtra("MyObject", user);
            intent.putExtra("From", comesFromUser);
            startActivity(intent);
        }

        public void goToLogin (View view){
            startActivity(new Intent(UserProfile.this, LoginActivity.class));
        }

        public void goToFoodTracker (View view){
            startActivity(new Intent(UserProfile.this, FoodTrackerActivity.class));
        }
    }