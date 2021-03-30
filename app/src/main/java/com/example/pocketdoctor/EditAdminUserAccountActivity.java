package com.example.pocketdoctor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketdoctor.repository.DatabaseHelper;

public class EditAdminUserAccountActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    String currentName;
    String currentLastName;
    String currentEmail;
    String currentMSP;
    EditText name;
    EditText lastName;
    EditText email;
    EditText msp;
    String userId;
    PocketDoctorApplication user;
    Button saveChanges;
    String comesFrom;
    //used to make a email pattern validation in the email
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ImageView homeImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin_user_account_view);
        homeImage = findViewById(R.id.imageViewHome);
        Button buttonCancelEdition = findViewById(R.id.btnCancelUserEdit);
        saveChanges = findViewById(R.id.btnSaveUserEdit);
        databaseHelper = new DatabaseHelper(this);

        name = findViewById(R.id.editUserName);
        lastName = findViewById(R.id.editUserLastName);
        email = findViewById(R.id.editUserEmail);
        msp = findViewById(R.id.editUserMsp);


        user = (PocketDoctorApplication) getIntent().getSerializableExtra("MyObject");
        name.setText(user.getCurrentUserName());
        lastName.setText(user.getCurrentUserLastName());
        email.setText(user.getCurrentUserEmail());
        msp.setText(user.getCurrentMSP());

        if (getIntent().getExtras() != null) {
            comesFrom = getIntent().getExtras().getString("From");
        }
        

        saveChanges.setEnabled(false);


        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(lastName.getText())
                        || TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(msp.getText())) {
                    name.setError("There is an empty field");
                    saveChanges.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(lastName.getText())
                        || TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(msp.getText())) {
                    saveChanges.setBackgroundResource(R.drawable.rectangle_round_button_fill);
                    saveChanges.setTextColor(Color.parseColor("#828282"));
                    saveChanges.setEnabled(false);
                } else {
                    saveChanges.setBackgroundResource(R.drawable.rectangle_round_button_fill_brown);
                    saveChanges.setTextColor(Color.parseColor("#FFFFFF"));
                    saveChanges.setEnabled(true);
                }

            }
        });
        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(lastName.getText()) || TextUtils.isEmpty(name.getText()) ||
                        TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(msp.getText())) {
                    lastName.setError("There is an empty field");
                    saveChanges.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(lastName.getText()) || TextUtils.isEmpty(name.getText()) ||
                        TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(msp.getText())) {
                    saveChanges.setBackgroundResource(R.drawable.rectangle_round_button_fill);
                    saveChanges.setTextColor(Color.parseColor("#828282"));
                    saveChanges.setEnabled(false);
                } else {
                    saveChanges.setBackgroundResource(R.drawable.rectangle_round_button_fill_brown);
                    saveChanges.setTextColor(Color.parseColor("#FFFFFF"));
                    saveChanges.setEnabled(true);
                }
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(lastName.getText()) || TextUtils.isEmpty(name.getText()) ||
                        TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(msp.getText())) {
                    email.setError("Enter a valid email");
                    saveChanges.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(lastName.getText()) || TextUtils.isEmpty(name.getText()) ||
                        TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(msp.getText())) {
                    saveChanges.setBackgroundResource(R.drawable.rectangle_round_button_fill);
                    saveChanges.setTextColor(Color.parseColor("#828282"));
                    saveChanges.setEnabled(false);
                } else {
                    saveChanges.setBackgroundResource(R.drawable.rectangle_round_button_fill_brown);
                    saveChanges.setTextColor(Color.parseColor("#FFFFFF"));
                    saveChanges.setEnabled(true);
                }
            }
        });

        msp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(lastName.getText()) || TextUtils.isEmpty(name.getText()) ||
                        TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(msp.getText())) {
                    msp.setError("Type yes or no");
                    saveChanges.setEnabled(false);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (TextUtils.isEmpty(lastName.getText()) || TextUtils.isEmpty(name.getText()) ||
                        TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(msp.getText())) {
                    saveChanges.setBackgroundResource(R.drawable.rectangle_round_button_fill);
                    saveChanges.setTextColor(Color.parseColor("#828282"));
                    saveChanges.setEnabled(false);
                } else {
                    saveChanges.setBackgroundResource(R.drawable.rectangle_round_button_fill_brown);
                    saveChanges.setTextColor(Color.parseColor("#FFFFFF"));
                    saveChanges.setEnabled(true);
                }
            }
        });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = (PocketDoctorApplication) getIntent().getSerializableExtra("MyObject");
                userId = user.getCurrentUserId();
                currentName = name.getText().toString();
                currentLastName = lastName.getText().toString();
                currentEmail = email.getText().toString();
                currentMSP = msp.getText().toString().toLowerCase();

                boolean isUpdated = databaseHelper.upDate(userId, currentName, currentLastName, currentEmail, currentMSP);

                if (isUpdated) {
                    Toast.makeText(EditAdminUserAccountActivity.this, "Record Updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(EditAdminUserAccountActivity.this, "Record not Updated", Toast.LENGTH_LONG).show();
                    if (!msp.getText().toString().equalsIgnoreCase("yes") && !msp.getText().toString().equalsIgnoreCase("no")) {
                        msp.setText("");
                    }
                    if (!email.getText().toString().matches(emailPattern)) {
                        email.setText("");
                    }
                }
            }
        });

        buttonCancelEdition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(comesFrom)) {
                    startActivity(new Intent(EditAdminUserAccountActivity.this, UserMain.class));
                }else if(TextUtils.isEmpty(comesFrom) || comesFrom == null)
                {
                    startActivity(new Intent(EditAdminUserAccountActivity.this, HomeAdmin.class));
                }
            }
        });
    }

    public void goToLogin(View view) {
        startActivity(new Intent(EditAdminUserAccountActivity.this, LoginActivity.class));
    }
    public void goToHome(View view){
        if (!TextUtils.isEmpty(comesFrom)) {
            homeImage.setColorFilter(Color.parseColor("#222222"));
            startActivity(new Intent(EditAdminUserAccountActivity.this, UserMain.class));
        }else if(TextUtils.isEmpty(comesFrom) || comesFrom == null)
        {
            homeImage.setColorFilter(Color.parseColor("#222222"));
            startActivity(new Intent(EditAdminUserAccountActivity.this, HomeAdmin.class));
        }

    }
}