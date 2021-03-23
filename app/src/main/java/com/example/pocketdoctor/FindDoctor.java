package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FindDoctor extends AppCompatActivity {

    EditText city;
    String stringCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        city = findViewById(R.id.editTextAddress);
    }

    public void gotoListOfDoctors(View view) {
        Intent i = new Intent(FindDoctor.this, ListOfDoctors.class);
        stringCity = city.getText().toString();
        i.putExtra("ValueCity",stringCity);
        startActivity(i);

    }



   // TODO -->
    public void gotoProfileActivity(View view) {
        startActivity(new Intent(FindDoctor.this, ListOfDoctors.class));
    }
}