package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.pocketdoctor.repository.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DoctorMessageActivity extends AppCompatActivity {
    ImageView calorieIcon;
    ImageView homeIcon;
    ImageView stethoscopeIcon;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_message);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        String[] fromKeyProperty = new String[] {"image", "messageStatus", "address", "messageDate"};
        int[] toResourceId = new int[] {R.id.doctorImage, R.id.messageStatus, R.id.address, R.id.messageDate};

        List<HashMap<String, String>> doctorList = new ArrayList<HashMap<String, String>>();
        userId = ((PocketDoctorApplication)getApplication()).getCurrentUserId();

        Cursor cursor = databaseHelper.getDoctorMessageForUserId(userId);
        cursor.moveToFirst();
        do  {
            HashMap<String, String> doctor = new HashMap<String, String>();
            doctor.put("image", String.valueOf(R.drawable.avatarimage)); //doctor name
            doctor.put("address", cursor.getString(0) + "\n" + cursor.getString(1)); //address
            doctor.put("messageStatus", cursor.getString(2)); //message content
            doctor.put("messageDate", cursor.getString(4));
            doctor.put("doctor_id", cursor.getString(5));
            doctorList.add(doctor);
        } while (cursor.moveToNext());

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, doctorList, R.layout.listview_doctor_message_items, fromKeyProperty, toResourceId);

        ListView listView = findViewById(R.id.lstDoctorMessages);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String doctorId = doctorList.get(position).get("doctor_id");
            Intent intent = new Intent(DoctorMessageActivity.this, MessageContentActivity.class);
            intent.putExtra("sender", userId);
            intent.putExtra("replier", doctorId);
            intent.putExtra("user_view", true);
            startActivity(intent);
        });



    }
    public void gotoLoginActivity(View view){
        startActivity(new Intent(DoctorMessageActivity.this, LoginActivity.class));
    }
}