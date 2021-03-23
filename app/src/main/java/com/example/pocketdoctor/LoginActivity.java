package com.example.pocketdoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pocketdoctor.repository.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText edtUsername;
    EditText edtPassword;
    TextView showHidePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.editTextTextEmailAddress);
        edtPassword = findViewById(R.id.editTextTextPassword);
        showHidePass = findViewById(R.id.showHidePass2);

        this.deleteDatabase("pocket_docter"); // Delete and recreate database
        databaseHelper = new DatabaseHelper(this);
    }

    public void signIn(View view) {
        edtUsername.setError(null);
        edtPassword.setError(null);
        Cursor currentUser = this.databaseHelper.findUser(edtUsername.getText().toString(), edtPassword.getText().toString());
        if (currentUser.getCount() > 0) {
            currentUser.moveToFirst();
            String userId = currentUser.getString(0);
            ((PocketDoctorApplication)getApplication()).setCurrentUserId(userId);
            gotoMainActivity();
        } else {
            showSignInError();
        }
    }

    private void gotoMainActivity() {
//        startActivity(new Intent(LoginActivity.this, HomeAdmin.class));
        startActivity(new Intent(LoginActivity.this, FoodTrackerActivity.class));
    }

    //Show & Hide Password
    public void showPassword(View view){
        if(showHidePass.getText().equals("Show")){
            edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            showHidePass.setText("Hide");
        } else
        {

            edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            showHidePass.setText("Show");
        }
    }


    private void showSignInError() {
        edtUsername.setError(getString(R.string.sign_in_error));
        edtPassword.setError(getString(R.string.sign_in_error));
    }

    public void gotoForgotPasswordActivity(View view) {
        //TODO: goto forgot password activity
    }

    public void gotoSignUpActivity(View view) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}