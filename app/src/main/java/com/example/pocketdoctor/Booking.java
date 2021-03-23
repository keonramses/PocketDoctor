package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pocketdoctor.repository.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Booking extends AppCompatActivity {
    String docId;
    DatabaseHelper databaseHelper;
    TextView docOutput;
    String userId;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    Button bookAppointment, onlineHelp;
    String date;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

         docId = getIntent().getExtras().getString("textValue");
         docOutput = findViewById(R.id.textViewOutputDoctor);
         databaseHelper = new DatabaseHelper(this);
         getDoctor();

         bookAppointment = findViewById(R.id.buttonBook);
         onlineHelp = findViewById(R.id.buttonOnlineHelp);


        userId = ((PocketDoctorApplication)getApplication()).getCurrentUserId();
        userId = "7d1bb54a-533b-42db-897d-a6cff78c89a7";

        bookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
               DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       calendar.set(Calendar.YEAR, year);
                       calendar.set(Calendar.MONTH, month);
                       calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");


                       /*Intent intent = new Intent(Booking.this, Patient_BookAppointmentActivity.class);
                       intent.putExtra("Date",simpleDateFormat.format(calendar.getTime()));
                       intent.putExtra("UserId",userId);
                       startActivity(intent);*/
                       date = simpleDateFormat.format(calendar.getTime());
                   }
               };
             new DatePickerDialog(Booking.this, dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void getDoctor(){
        Cursor c = databaseHelper.findDoctor(docId);
        StringBuilder str = new StringBuilder();
        if(c.getCount()>0){
            while (c.moveToNext()){
                str.append("Dr " + c.getString(0));
                str.append(" " + c.getString(1));
                str.append("\n");
                str.append(c.getString(2));
            }
            docOutput.setText(str);
        }
    }

    public void gotoFindDoctor(View view) {
        startActivity(new Intent(Booking.this, ListOfDoctors.class));
    }
}