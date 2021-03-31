package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pocketdoctor.repository.DatabaseHelper;

public class HomeAdmin extends AppCompatActivity {

    int userType;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

    }
    //Decide which activity to open depending on the button, and it also assign a value
    //to a variable called buttonOption to track what label should be gender on the Top of the Page
    public void displayUsersActivities(View view)
    {
        Intent i = new Intent(HomeAdmin.this,PatientAccountActivity.class);

        switch(view.getId())
        {
            case R.id.buttonPatientAccount:
                //i.putExtra("buttonOption", 1);
                userType = 1;
                ((PocketDoctorApplication)getApplication()).setCurrentUserType(userType);
                break;
            case R.id.buttonDoctorAccount:
                //i.putExtra("buttonOption", 2);
                userType = 2;
                ((PocketDoctorApplication)getApplication()).setCurrentUserType(userType);
                break;
            case R.id.buttonCashierAccount:
                userType = 3;
                //i.putExtra("buttonOption", 3);
                ((PocketDoctorApplication)getApplication()).setCurrentUserType(userType);
                break;
        }
        startActivity(i);
    }

    public void goToLogin(View view) {
        startActivity(new Intent(HomeAdmin.this, LoginActivity.class));
    }
}
