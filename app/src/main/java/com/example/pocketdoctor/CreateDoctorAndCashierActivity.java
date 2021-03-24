package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pocketdoctor.repository.DatabaseHelper;

import java.util.UUID;

public class CreateDoctorAndCashierActivity extends AppCompatActivity {

    TextView show;
    TextView title;
    ImageView imageHome;
    Button buttonSaveUser;
    Button buttonCancel;
    EditText name;
    EditText email;
    EditText lastName;
    EditText password;
    EditText address;
    String nameToSend;
    String emailToSend;
    String lastNameToSend;
    String passwordToSend;
    String addressToSend;
    int userType;
    DatabaseHelper databaseHelper;
    UUID userId;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_doctor_and_cashier);

        title = findViewById(R.id.createAcctDocTitle);
        imageHome = findViewById(R.id.imageViewHome);
        buttonCancel = findViewById(R.id.btnCancelDocAccCreation);
        buttonSaveUser = findViewById(R.id.btnSaveAccDocCreation);
        name = findViewById(R.id.createAccDocName);
        lastName = findViewById(R.id.createAccDocLastName);
        email = findViewById(R.id.createAccDocEmail);
        password = findViewById(R.id.createAccDocPasswo);
        address = findViewById(R.id.createAccDocAddress);
        show = findViewById(R.id.idShow);

        databaseHelper = new DatabaseHelper(this);

        buttonSaveUser.setEnabled(false);

        Intent i = getIntent();
        if (i != null) {
            if (((PocketDoctorApplication) getApplication()).getCurrentUserType() == 2) {
                title.setText("Create Doctor Account");
            } else if (((PocketDoctorApplication) getApplication()).getCurrentUserType() == 3) {
                title.setText("Create Cashier Account");
            }
        }

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
                addressToSend = address.getText().toString();
                userId = UUID.randomUUID();
                userType = ((PocketDoctorApplication) getApplication()).getCurrentUserType();
                String userIdToSend = userId.toString();

                boolean isAdded = databaseHelper.insertDoctorAndCashier(userIdToSend, nameToSend, lastNameToSend, emailToSend, passwordToSend, addressToSend, userType);
                if (isAdded) {
                    Toast.makeText(CreateDoctorAndCashierActivity.this, "Record Added", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(CreateDoctorAndCashierActivity.this, "Record not Added", Toast.LENGTH_LONG).show();
                if (!email.getText().toString().matches(emailPattern)) {
                    email.setText("");
                }
            }
        });


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateDoctorAndCashierActivity.this, PatientAccountActivity.class));
            }
        });

        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageHome.setColorFilter(Color.parseColor("#222222"));
                startActivity(new Intent(CreateDoctorAndCashierActivity.this, HomeAdmin.class));
            }
        });
    }

    //Show & Hide Password
    public void showPassword(View view) {
        if (show.getText().equals("Show")) {
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            show.setText("Hide");
        } else {
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            show.setText("Show");
        }
    }
}