package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pocketdoctor.repository.DatabaseHelper;

public class BookingPatientMessage extends AppCompatActivity {

    String date;
    String doctorId;
    TextView doctor;
    EditText message;
    Button btnSendMessage;
    TextView appointmentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_patient_message);
        doctor = findViewById(R.id.doctorInfo);
        btnSendMessage = findViewById(R.id.buttonSendMessage);
        message = findViewById(R.id.edtMessage);
        appointmentInfo = findViewById(R.id.appointmentInfo);
        date = "";
        if (getIntent().getExtras() != null) {
            date = getIntent().getExtras().getString("date");
            doctor.setText(getIntent().getExtras().getString("doctorInfo"));;
            doctorId =  getIntent().getExtras().getString("doctorId");
            appointmentInfo.setText("Appointment Date: " + date);
        }

        btnSendMessage.setOnClickListener(v -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(BookingPatientMessage.this);
            String userId = ((PocketDoctorApplication)getApplication()).getCurrentUserId();
            boolean success;
            try {
                success = databaseHelper.insertDoctorAppointment(userId, doctorId, date, message.getText().toString());
            } catch (SQLiteConstraintException ex) {
                success = false;
            }
            if (success) {
                Toast.makeText(BookingPatientMessage.this, "Your message was sent to the doctor", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(BookingPatientMessage.this, "Sorry, just 1 message a day.", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void goback(View view) {
        this.onBackPressed();
    }
}