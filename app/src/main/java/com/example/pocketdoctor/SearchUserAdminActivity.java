package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.pocketdoctor.repository.DatabaseHelper;

public class SearchUserAdminActivity extends AppCompatActivity {
    SearchView searchUserView;
    ImageView home;
    DatabaseHelper database;
    int currentUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user_admin_activity);

        currentUserType = ((PocketDoctorApplication) getApplication()).getCurrentUserType();
        searchUserView = findViewById(R.id.idSearchUser);

        home = findViewById(R.id.imageViewHome);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchUserAdminActivity.this, HomeAdmin.class));
            }
        });

        initialListOfUser();
    }

    private void initialListOfUser() {
        database = new DatabaseHelper(SearchUserAdminActivity.this);

        searchUserView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContact(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    private void searchContact(String email) {
        database = new DatabaseHelper(SearchUserAdminActivity.this);
        int userType = ((PocketDoctorApplication)getApplication()).getCurrentUserType();
        PocketDoctorApplication contact = database.searchUser(email, userType);
        if (contact != null) {
            if(((PocketDoctorApplication) getApplication()).getCurrentUserType() == 2 || ((PocketDoctorApplication) getApplication()).getCurrentUserType() == 3)
            {
                startActivity(new Intent(SearchUserAdminActivity.this,EditDoctorAndCashierActivity.class)
                        .putExtra("MyObject", contact));
            }
            else
            startActivity(new Intent(SearchUserAdminActivity.this, EditAdminUserAccountActivity.class)
                    .putExtra("MyObject", contact));
        }else
            Toast.makeText(SearchUserAdminActivity.this, "Incorrect Email Address", Toast.LENGTH_LONG).show();
    }

}