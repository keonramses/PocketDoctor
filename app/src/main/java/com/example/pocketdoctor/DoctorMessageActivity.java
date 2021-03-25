package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DoctorMessageActivity extends AppCompatActivity {
    ImageView calorieIcon;
    ImageView homeIcon;
    ImageView stethoscopeIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_message);

        calorieIcon = findViewById(R.id.imageViewFood);
        homeIcon = findViewById(R.id.imageViewHome);
        stethoscopeIcon = findViewById(R.id.imageViewStethoScope);

        //CONTEXTUAL MENU ICON FUNCTION
        calorieIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorMessageActivity.this, FoodTrackerActivity.class));
            }
        });
        //CONTEXTUAL MENU ICON FUNCTION
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorMessageActivity.this, UserMain.class));
            }
        });
        //CONTEXTUAL MENU ICON FUNCTION
        stethoscopeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorMessageActivity.this, FindDoctor.class));
            }
        });
    }
}