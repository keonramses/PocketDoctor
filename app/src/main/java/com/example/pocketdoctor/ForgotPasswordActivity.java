package com.example.pocketdoctor;

import android.content.ContentValues;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketdoctor.repository.DatabaseHelper;

public class ForgotPasswordActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText password, confirm_password, email;
    Button resetPassword;
    TextView showHidePassword, login;
    String newPassword;
    String cEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        password = findViewById(R.id.editTextTextPassword);
        confirm_password = findViewById(R.id.editTextTextPasswordConfirm);
        resetPassword = findViewById(R.id.buttonResetPassword);
        showHidePassword = findViewById(R.id.showHidePass2);
        login = findViewById(R.id.txtLogin);
        databaseHelper = new DatabaseHelper(this);
        email = findViewById(R.id.editTextTextEmail);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
            }
        });

        showHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showHidePassword.getText().equals("Show")){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    confirm_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showHidePassword.setText("Hide");
                } else
                {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    confirm_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showHidePassword.setText("Show");
                }
            }
        });


    }

    public void resetPassword(View view) {
        checkDataEntered();
        newPassword = password.getText().toString();
        cEmail = email.getText().toString();

        Cursor currentUser = this.databaseHelper.resetUser(cEmail);
        if(currentUser.getCount() > 0){
            currentUser.moveToFirst();
            if (checkDataEntered() == true){
                databaseHelper.resetPassword(newPassword,cEmail);
            Toast.makeText(ForgotPasswordActivity.this, "Password Changed", Toast.LENGTH_LONG).show();
            startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
        }
        }else{
            Toast.makeText(ForgotPasswordActivity.this, "Password Reset Failed Please Try Again", Toast.LENGTH_LONG).show();
        }
        currentUser.close();
    }

    public boolean isEmail(EditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }


    public boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }


    public boolean checkDataEntered(){
        if (isEmail(email) == false){
            email.setError("Enter valid email!");
            return false;
        }
        if(isEmpty(password)){
            password.setError("This Field is Required");
            return false;
        }
        if(isEmpty(confirm_password)){
            confirm_password.setError("This Field is Required");
            return false;
        }
        if (!(password.getText().toString().equals(confirm_password.getText().toString()))){
            Toast.makeText(getApplicationContext(), "Passwords does not match!",
                    Toast.LENGTH_LONG).show();
            password.setError("Passwords do not match");
            confirm_password.setError("Passwords do not match");
            return false;
        }
        else{
            password.setError(null);
            confirm_password.setError(null);
            return true;
        }
    }

    public void clearFields(View view) {
        password.getText().clear();
        confirm_password.getText().clear();
    }
}
