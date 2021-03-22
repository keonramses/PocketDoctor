package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.pocketdoctor.repository.DatabaseHelper;

public class EditDoctorAndCashierActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    String currentName;
    String currentLastName;
    String currentEmail;
    String currentAddress;
    EditText name;
    EditText lastName;
    EditText email;
    EditText address;
    String userId;
    TextView title;
    PocketDoctorApplication user;
    Button saveChanges;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_doctor_and_cashier);

        ImageView homeImage = findViewById(R.id.imageViewHome);
        Button buttonCancelEdition = findViewById(R.id.btnAccDocCancelEdit);
        saveChanges = findViewById(R.id.btnAccDocSaveEdit);
        databaseHelper = new DatabaseHelper(this);

        name = findViewById(R.id.editAccDocName);
        lastName = findViewById(R.id.editAccDocLastName);
        email = findViewById(R.id.editAccDocEmail);
        address = findViewById(R.id.editAccDocAddress);
        title = findViewById(R.id.editAccDocTitle);

        //used to get the object as a result from the search query
        user = (PocketDoctorApplication) getIntent().getSerializableExtra("MyObject");

        name.setText(user.getCurrentUserName());
        lastName.setText(user.getCurrentUserLastName());
        email.setText(user.getCurrentUserEmail());
        address.setText(user.getCurrentAddress());


        //Set the title of the activity based on the current user type
        Intent i = getIntent();
        if(i!=null) {
            if (((PocketDoctorApplication) getApplication()).getCurrentUserType() == 2) {
                title.setText("Edit Doctor Account");
            } else if (((PocketDoctorApplication) getApplication()).getCurrentUserType() == 3) {
                title.setText("Edit Cashier Account");
            }
        }

        saveChanges.setEnabled(false);

        //All these event listener handles the textchange in the edit text to make some user input
        //validation
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(lastName.getText())
                        || TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(address.getText())) {
                    name.setError("There is an empty field");
                    saveChanges.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(lastName.getText())
                        || TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(address.getText())) {
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
                if (TextUtils.isEmpty(lastName.getText())||TextUtils.isEmpty(name.getText())||
                        TextUtils.isEmpty(email.getText())||TextUtils.isEmpty(address.getText()) ) {
                    lastName.setError("There is an empty field");
                    saveChanges.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(lastName.getText())||TextUtils.isEmpty(name.getText())||
                        TextUtils.isEmpty(email.getText())||TextUtils.isEmpty(address.getText())) {
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
                if (TextUtils.isEmpty(lastName.getText())||TextUtils.isEmpty(name.getText())||
                        TextUtils.isEmpty(email.getText())||TextUtils.isEmpty(address.getText())) {
                    email.setError("Enter a valid email");
                    saveChanges.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(lastName.getText())||TextUtils.isEmpty(name.getText())||
                        TextUtils.isEmpty(email.getText())||TextUtils.isEmpty(address.getText())) {
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

        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(lastName.getText())||TextUtils.isEmpty(name.getText())||
                        TextUtils.isEmpty(email.getText())||TextUtils.isEmpty(address.getText())) {
                    address.setError("Type yes or no");
                    saveChanges.setEnabled(false);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (TextUtils.isEmpty(lastName.getText())||TextUtils.isEmpty(name.getText())||
                        TextUtils.isEmpty(email.getText())||TextUtils.isEmpty(address.getText())) {
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
                currentAddress = address.getText().toString().toLowerCase();

                boolean isUpdated = databaseHelper.upDateDoctorAndCashier(userId, currentName, currentLastName, currentEmail, currentAddress);

                if (isUpdated) {
                    Toast.makeText(EditDoctorAndCashierActivity.this, "Record Updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(EditDoctorAndCashierActivity.this, "Record not Updated", Toast.LENGTH_LONG).show();
                    if(!email.getText().toString().matches(emailPattern))
                    {
                        email.setText("");
                    }
                }
            }
        });

        buttonCancelEdition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditDoctorAndCashierActivity.this, PatientAccountActivity.class));
            }
        });
        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeImage.setColorFilter(Color.parseColor("#222222"));
                startActivity(new Intent(EditDoctorAndCashierActivity.this, HomeAdmin.class));
            }
        });
    }

}