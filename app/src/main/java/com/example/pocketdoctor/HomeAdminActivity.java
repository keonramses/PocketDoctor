package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //Decide which activity to open depending on the button, and it also assign a value
    //to a variable called buttonOption to track what label should be gender on the Top of the Page
    public void displayUsersActivities(View view)
    {
        Intent i = new Intent(HomeAdminActivity.this,PatientAccountActivity.class);

        switch(view.getId())
        {
            case R.id.buttonPatientAccount:
                i.putExtra("buttonOption", 1);
                break;
            case R.id.buttonDoctorAccount:
                i.putExtra("buttonOption", 2);
                break;
            case R.id.buttonCashierAccount:
                i.putExtra("buttonOption", 3);
                break;
        }
        startActivity(i);
    }
}