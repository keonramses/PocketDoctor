package com.example.pocketdoctor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PatientAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        //The changing title
        TextView screenView = findViewById(R.id.idAdminUserActionsHomePage);

        ImageView homeImage = findViewById(R.id.imageViewHome);
        ImageView imageCreate = findViewById(R.id.imageCreateUser);
        ImageView imageEdit = findViewById(R.id.imageEditUser);

        //This Simple Validation verifies which button was pressed and depending on that assign
        //the text to the top of the screen

        Intent i = getIntent();
        if(i!=null)
        {
            int buttonOption = getIntent().getIntExtra("buttonOption",0);
            if(buttonOption == 1)
            {
                screenView.setText("User Account");
            }
            else if(buttonOption == 2)
            {
                screenView.setText("Doctor Account");
                imageCreate.setImageResource(R.drawable.plusdoctoricon);
                imageEdit.setImageResource(R.drawable.pencildoctor);
            }
            else if(buttonOption == 3)
            {
                screenView.setText("Cashier Account");
                imageCreate.setImageResource(R.drawable.plusiconcashier);
                imageEdit.setImageResource(R.drawable.pencilcashier);
            }
        }

        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeImage.setColorFilter(Color.parseColor("#222222"));
                startActivity(new Intent(PatientAccountActivity.this, HomeAdmin.class));
            }
        });
    }
    public void displayOption(View view)
    {
        switch (view.getId()){
            case R.id.idCreateNewUserAccountView:
                startActivity(new Intent(PatientAccountActivity.this, CreateAdminUserAccountActivity.class));
                break;
            case R.id.idEditUserAccountView:
                startActivity(new Intent(PatientAccountActivity.this, SearchUserIdActivity.class));
                break;
        }


    }
}