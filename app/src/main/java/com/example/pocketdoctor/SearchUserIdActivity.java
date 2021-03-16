package com.example.pocketdoctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class SearchUserIdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user_id);
        ImageView homeImage = findViewById(R.id.imageViewHome);
        SearchView searchId = findViewById(R.id.idSearchBox);
        View foundUserView = findViewById(R.id.idViewFoundUser);

        foundUserView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchUserIdActivity.this, EditAdminUserAccountActivity.class));
            }
        });

        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchUserIdActivity.this, HomeAdmin.class));
            }
        });
    }
}