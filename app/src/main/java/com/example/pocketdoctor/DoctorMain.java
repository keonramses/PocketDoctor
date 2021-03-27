package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.pocketdoctor.repository.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DoctorMain extends AppCompatActivity {

    SimpleAdapter simpleAdapter;
    ListView listView;
    List<HashMap<String, String>> messageList;
    String[] fromPropertyKey;
    int[] toResourceId;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        fromPropertyKey = new String[]{"image", "messageContent", "address", "messageDate"};
        toResourceId = new int[]{R.id.patientImage, R.id.messageContent, R.id.address, R.id.messageDate};

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        userId = ((PocketDoctorApplication)getApplication()).getCurrentUserId(); // this user is a doctor
        messageList = new ArrayList<HashMap<String, String>>();
        Cursor cursor = databaseHelper.getMessageForDoctorById(userId);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> message = new HashMap<String, String>();
                message.put("image", String.valueOf(R.drawable.avatarimage)); //patient image
                message.put("address", cursor.getString(0) + "\n" + cursor.getString(1)); //address
                message.put("messageContent", cursor.getString(2)); //message content
                message.put("messageDate", cursor.getString(4));
                message.put("patient_id", cursor.getString(6));
                messageList.add(message);
            } while (cursor.moveToNext());
        }

        simpleAdapter = new SimpleAdapter(this, messageList, R.layout.listview_patient_message_item, fromPropertyKey, toResourceId);

        listView = findViewById(R.id.lstPatientMessages);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String patientId = messageList.get(position).get("patient_id");
            Intent intent = new Intent(DoctorMain.this, MessageContentActivity.class);
            intent.putExtra("sender", patientId);
            intent.putExtra("replier", userId);
            intent.putExtra("user_view", false);
            startActivity(intent);
        });

    }
}