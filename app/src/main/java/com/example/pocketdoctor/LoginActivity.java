package com.example.pocketdoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pocketdoctor.repository.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText edtUsername;
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.editTextTextEmailAddress);
        edtPassword = findViewById(R.id.editTextTextPassword);

        this.deleteDatabase("pocket_docter"); // Delete and recreate database
        databaseHelper = new DatabaseHelper(this);
    }

    public void signIn(View view) {
        edtUsername.setError(null);
        edtPassword.setError(null);
        boolean isUserExist = this.databaseHelper.findUser(edtUsername.getText().toString(), edtPassword.getText().toString());
        if (isUserExist) {
            gotoMainActivity();
        } else {
            showSignInError();
        }
    }

    private void gotoMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    private void showSignInError() {
        edtUsername.setError(getString(R.string.sign_in_error));
        edtPassword.setError(getString(R.string.sign_in_error));
    }

    public void gotoForgotPasswordActivity(View view) {
        //TODO: goto forgot password activity
    }

    public void gotoSignUpActivity(View view) {
        //TODO: navigate to sign up activity
        Toast.makeText(LoginActivity.this, "got to gotoSignUpActivity", Toast.LENGTH_SHORT).show();
    }
}