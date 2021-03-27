package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pocketdoctor.repository.DatabaseHelper;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    DatabaseHelper databaseHelper;
    Spinner msp;
    TextView showHidePass;
    UUID newUserId;
    String sNewUserId;
    int usertype = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        firstName = findViewById(R.id.editTextTextFirstName);
        lastName = findViewById(R.id.editTextTextLastName);
        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        msp = findViewById(R.id.mspRegister);
        showHidePass = findViewById(R.id.showHidePass);
    }


    public void signup(View view){
        checkDataEntered();
        newUserId = UUID.randomUUID();
        sNewUserId = newUserId.toString();
        ((PocketDoctorApplication)getApplication()).setCurrentUserId(sNewUserId);
        String fname = firstName.getText().toString();
        String lname = lastName.getText().toString();
        String sEmail = email.getText().toString();
        String passw = password.getText().toString();
        String mspr = msp.getSelectedItem().toString();


        Cursor finduser = this.databaseHelper.findUser(sEmail,passw);
        if(!(finduser.getCount() > 0)){
            boolean insert = this.databaseHelper.insertData(sNewUserId, fname, lname, sEmail, passw, mspr, usertype);
            if(insert){
                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, UserMain.class));
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

//Show & Hide Password
    public void showPassword(View view){
        if(showHidePass.getText().equals("Show")){
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            showHidePass.setText("Hide");
        } else
        {
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            showHidePass.setText("Show");
        }
    }

    public void gotoLoginActivity(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void clearFields(View view) {
        firstName.getText().clear();
        lastName.getText().clear();
        email.getText().clear();
        password.getText().clear();
    }
}