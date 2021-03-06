package com.example.pocketdoctor;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketdoctor.repository.DatabaseHelper;

import java.util.UUID;

public class CreateAdminUserAccountActivity extends AppCompatActivity {
    EditText name;
    EditText email;
    EditText lastName;
    EditText password;
    Button buttonSaveUser;
    Button buttonCancel;
    ImageView homeImage;
    Spinner msp;
    String nameToSend;
    String emailToSend;
    String lastNameToSend;
    String passwordToSend;
    String mspStatus;
    int userType;
    DatabaseHelper databaseHelper;
    UUID userId;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_account);

        databaseHelper = new DatabaseHelper(this);

        homeImage = findViewById(R.id.imageViewHome);
        buttonSaveUser = findViewById(R.id.btnSaveUserCreation);
        buttonCancel = findViewById(R.id.btnCancelUserCreation);
        name = findViewById(R.id.editTextPersonName);
        email = findViewById(R.id.editTexCreateUserEmailAddress);
        lastName = findViewById(R.id.createUserLastName);
        password = findViewById(R.id.createPassword);
        msp = findViewById(R.id.spinnerMsp);


        buttonSaveUser.setEnabled(false);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(lastName.getText())
                        || TextUtils.isEmpty(email.getText())) {
                    name.setError("There is an empty field");
                    buttonSaveUser.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(lastName.getText())
                        || TextUtils.isEmpty(email.getText())) {
                    name.setError("This field cant be empty");
                    buttonSaveUser.setBackgroundResource(R.drawable.rectangle_round_button_fill);
                    buttonSaveUser.setTextColor(Color.parseColor("#828282"));
                    buttonSaveUser.setEnabled(false);

                } else {
                    buttonSaveUser.setBackgroundResource(R.drawable.rectangle_round_button_fill_brown);
                    buttonSaveUser.setTextColor(Color.parseColor("#FFFFFF"));
                    buttonSaveUser.setEnabled(true);
                }
            }
        });
        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(lastName.getText())
                        || TextUtils.isEmpty(email.getText())) {
                    lastName.setError("There is an empty field");
                    buttonSaveUser.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(lastName.getText())
                        || TextUtils.isEmpty(email.getText())) {
                    lastName.setError("This field cant be empty");
                    buttonSaveUser.setBackgroundResource(R.drawable.rectangle_round_button_fill);
                    buttonSaveUser.setTextColor(Color.parseColor("#828282"));
                    buttonSaveUser.setEnabled(false);
                } else {
                    buttonSaveUser.setBackgroundResource(R.drawable.rectangle_round_button_fill_brown);
                    buttonSaveUser.setTextColor(Color.parseColor("#FFFFFF"));
                    buttonSaveUser.setEnabled(true);
                }
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(lastName.getText())
                        || TextUtils.isEmpty(email.getText())) {
                    lastName.setError("There is an empty field");
                    buttonSaveUser.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(lastName.getText())
                        || TextUtils.isEmpty(email.getText())) {
                    lastName.setError("This field cant be empty");
                    buttonSaveUser.setBackgroundResource(R.drawable.rectangle_round_button_fill);
                    buttonSaveUser.setTextColor(Color.parseColor("#828282"));
                    buttonSaveUser.setEnabled(false);
                } else {
                    buttonSaveUser.setBackgroundResource(R.drawable.rectangle_round_button_fill_brown);
                    buttonSaveUser.setTextColor(Color.parseColor("#FFFFFF"));
                    buttonSaveUser.setEnabled(true);
                }
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(lastName.getText())
                        || TextUtils.isEmpty(email.getText())) {
                    lastName.setError("There is an empty field");
                    buttonSaveUser.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(lastName.getText())
                        || TextUtils.isEmpty(email.getText())) {
                    lastName.setError("This field cant be empty");
                    buttonSaveUser.setBackgroundResource(R.drawable.rectangle_round_button_fill);
                    buttonSaveUser.setTextColor(Color.parseColor("#828282"));
                    buttonSaveUser.setEnabled(false);
                } else {
                    buttonSaveUser.setBackgroundResource(R.drawable.rectangle_round_button_fill_brown);
                    buttonSaveUser.setTextColor(Color.parseColor("#FFFFFF"));
                    buttonSaveUser.setEnabled(true);
                }
            }
        });


        buttonSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameToSend = name.getText().toString();
                emailToSend = email.getText().toString();
                lastNameToSend = lastName.getText().toString();
                passwordToSend = password.getText().toString();
                mspStatus = msp.getSelectedItem().toString().toLowerCase();
                userId = UUID.randomUUID();
                userType = ((PocketDoctorApplication)getApplication()).getCurrentUserType();
                String userIdToSend = userId.toString();

                boolean isAdded = databaseHelper.insertData(userIdToSend, nameToSend, lastNameToSend, emailToSend, passwordToSend, mspStatus, userType );
                if (isAdded) {
                    Toast.makeText(CreateAdminUserAccountActivity.this, "Record Added", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(CreateAdminUserAccountActivity.this, "Record not Added", Toast.LENGTH_LONG).show();
                if(!email.getText().toString().matches(emailPattern))
                {
                    email.setText("");
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateAdminUserAccountActivity.this, PatientAccountActivity.class));
            }
        });

        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeImage.setColorFilter(Color.parseColor("#222222"));
                startActivity(new Intent(CreateAdminUserAccountActivity.this, HomeAdmin.class));
            }
        });

    }

}