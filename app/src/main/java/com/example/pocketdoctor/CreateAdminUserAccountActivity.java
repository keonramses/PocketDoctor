package com.example.pocketdoctor;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAdminUserAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_account);

        ImageView homeImage = findViewById(R.id.imageViewHome);
        Button buttonSaveUser = findViewById(R.id.btnSaveUserCreation);
        Button buttonCancel = findViewById(R.id.btnCancelUserCreation);
        Drawable fillRectangleButton=(Drawable)getResources().getDrawable(R.drawable.rectangle_round_button_fill);
        Drawable clearRectangleButton=(Drawable)getResources().getDrawable(R.drawable.rectangle_round_button_clear);
        EditText name = findViewById(R.id.editTextPersonName);
        EditText email = findViewById(R.id.editTexCreateUserEmailAddress);
        EditText phone = findViewById(R.id.editTextPersonPhone);
        Spinner msp = findViewById(R.id.spinnerMsp);
        /*nameToSend = name.getText().toString();
        emailToSend = email.getText().toString();
        phoneToSend = phone.getText().toString();

        boolean valid;
        valid = editTextValidation(nameToSend,emailToSend ,phoneToSend);
        if(valid)
        {
            buttonSaveUser.setBackground(clearRectangleButton);
        }
        else {
            buttonSaveUser.setBackground(fillRectangleButton);
        }*/


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateAdminUserAccountActivity.this, PatientAccountActivity.class));
            }
        });

        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeImage.setColorFilter(Color.parseColor("#222222"));
                startActivity(new Intent(CreateAdminUserAccountActivity.this, HomeAdmin.class));
            }
        });

    }
    /*public boolean editTextValidation(String name, String phone, String email)
    {
        boolean valid;
        valid = (name != " " && phone != " " && email != " ") ? true : false;
        return valid;

    }*/
}