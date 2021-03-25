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
    final int NORMAL_USER = 1;
    final int DOCTOR_USER = 2;
    final int CASHIER_USER = 3;
    final int ADMIN_USER = 4;
    int user_type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.editTextTextEmailAddress);
        edtPassword = findViewById(R.id.editTextTextPassword);
        showHidePass = findViewById(R.id.showHidePass2);

//        this.deleteDatabase("pocket_docter"); // Delete and recreate database
        databaseHelper = new DatabaseHelper(this);


    }

    public void signIn(View view) {
        edtUsername.setError(null);
        edtPassword.setError(null);
        Cursor currentUser = this.databaseHelper.findUser(edtUsername.getText().toString(), edtPassword.getText().toString());
        if (currentUser.getCount() > 0) {
            currentUser.moveToFirst();
            String userId = currentUser.getString(0);
            user_type = currentUser.getInt(1);
            ((PocketDoctorApplication)getApplication()).setCurrentUserId(userId);
            gotoMainActivity(user_type);
        } else {
            showSignInError();
        }
    }

    private void gotoMainActivity(int user_type) {
//        startActivity(new Intent(LoginActivity.this, HomeAdmin.class));
        if(user_type == NORMAL_USER)
        {
            startActivity(new Intent(LoginActivity.this, UserMain.class));
        }
        else if(user_type == DOCTOR_USER)
        {
            startActivity(new Intent(LoginActivity.this, DoctorMain.class));
        }
        else if(user_type == CASHIER_USER)
        {
            startActivity(new Intent(LoginActivity.this, CashierMain.class));
        }
        else if(user_type == ADMIN_USER)
        {
            startActivity(new Intent(LoginActivity.this, HomeAdmin.class));
        }

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
    public void clearFields(View view) {
        edtUsername.getText().clear();
        edtPassword.getText().clear();
    }
}