package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class UserMain extends AppCompatActivity {

    Button foodTracker;
    Button findDoctor;
    Button viewMessage;
    ImageView calorieIcon;
    ImageView stethoscopeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        foodTracker = findViewById(R.id.buttonCaloriesTracker);
        findDoctor = findViewById(R.id.buttonFindADoctor);
        viewMessage = findViewById(R.id.buttonMessageFromDoctors);
        calorieIcon = findViewById(R.id.imageViewFood);
        stethoscopeIcon = findViewById(R.id.imageViewStethoScope);

        foodTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMain.this, FoodTrackerActivity.class));
            }
        });

        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMain.this, FindDoctor.class));
            }
        });

        viewMessage.setOnClickListener(v -> {
            startActivity(new Intent(UserMain.this, DoctorMessageActivity.class));
        });

        //CONTEXTUAL MENU ICON FUNCTION
        calorieIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMain.this, FoodTrackerActivity.class));
            }
        });

        //CONTEXTUAL MENU ICON FUNCTION
        stethoscopeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMain.this, FindDoctor.class));
            }
        });

    }

    public void goToProfile(View view){
        startActivity(new Intent(UserMain.this, UserProfile.class));
    }




}