package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pocketdoctor.repository.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        firstName = findViewById(R.id.editTextTextFirstName);
        lastName = findViewById(R.id.editTextTextLastName);
        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);


    }


    public void signup(View view){
        checkDataEntered();
        String fname = firstName.getText().toString();
        String lname = lastName.getText().toString();
        String sEmail = email.getText().toString();
        String passw = password.getText().toString();

        boolean finduser = this.databaseHelper.findUser(sEmail,passw);
        if(finduser == false){
            boolean insert = this.databaseHelper.insertData(fname, lname, sEmail, passw);
            if(insert){
                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                // TO DO
                //  startActivity(new Intent(MainActivity.this, toUSerMainActivity.class));
            }else {
                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_LONG).show();
            }
        } else{
           Toast.makeText(MainActivity.this, "User already exists!. Please log in",Toast.LENGTH_LONG).show();
        }
    }

    public boolean isEmail(EditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    public boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void checkDataEntered(){
        if(isEmpty(firstName)){
            Toast t = Toast.makeText(this, "You must enter first name to sign up!", Toast.LENGTH_LONG);
            t.show();
        }
        if(isEmpty(lastName)){
            lastName.setError("Last name is required");
        }
        if (isEmail(email) == false){
            email.setError("Enter valid email!");
        }
    }

    public void gotoLoginActivity(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}