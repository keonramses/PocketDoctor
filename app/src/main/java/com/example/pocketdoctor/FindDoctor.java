package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class FindDoctor extends AppCompatActivity {

    EditText city;
    String stringCity;
    ImageView calorieIcon;
    ImageView homeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        city = findViewById(R.id.editTextAddress);
        calorieIcon = findViewById(R.id.imageViewFood);
        homeIcon = findViewById(R.id.imageViewHome);

        //CONTEXTUAL MENU ICON FUNCTION
        calorieIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctor.this, FoodTrackerActivity.class));
            }
        });

        //CONTEXTUAL MENU ICON FUNCTION
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctor.this, UserMain.class));
            }
        });
    }

    public void gotoListOfDoctors(View view) {
        Intent i = new Intent(FindDoctor.this, ListOfDoctors.class);
        stringCity = city.getText().toString();
        i.putExtra("ValueCity",stringCity);
        startActivity(i);
    }

    public void gotoProfileActivity(View view) {
        startActivity(new Intent(FindDoctor.this, UserProfile.class));
    }
    public void gotoLoginActivity(View view){
        startActivity(new Intent(FindDoctor.this, LoginActivity.class));
    }
}