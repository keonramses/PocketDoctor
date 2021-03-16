package com.example.pocketdoctor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EditAdminUserAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin_user_account_view);
        ImageView homeImage = findViewById(R.id.imageViewHome);
        Button buttonCancelEdition = findViewById(R.id.btnCancelEdition);

        buttonCancelEdition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditAdminUserAccountActivity.this, PatientAccountActivity.class));
            }
        });

        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeImage.setColorFilter(Color.parseColor("#222222"));
                startActivity(new Intent(EditAdminUserAccountActivity.this, HomeAdmin.class));
            }
        });
    }
}