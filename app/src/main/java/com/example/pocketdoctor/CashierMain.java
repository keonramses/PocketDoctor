package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pocketdoctor.repository.DatabaseHelper;

import java.util.ArrayList;

public class CashierMain extends AppCompatActivity implements CashierAdapter.ItemClickListener {
    CashierAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<User> users;
    DatabaseHelper databaseHelper;
   ImageView homeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_main);

        databaseHelper = new DatabaseHelper(this);
        users = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        users = databaseHelper.getCashierList();
        adapter = new CashierAdapter(this, users);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(View view, int position) {

    }
    public void goToLogin(View view) {
        startActivity(new Intent(CashierMain.this, LoginActivity.class));
    }

}