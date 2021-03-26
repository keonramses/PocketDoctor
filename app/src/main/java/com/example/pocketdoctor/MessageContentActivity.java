package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.pocketdoctor.repository.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageContentActivity extends AppCompatActivity {

    ImageView calorieIcon;
    ImageView homeIcon;
    ImageView stethoscopeIcon;
    String doctorId;
    String userId;
    boolean userView = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_content);

        if (getIntent().getExtras() != null) {
            doctorId = getIntent().getExtras().getString("replier");
            userId = getIntent().getExtras().getString("sender");
            userView = getIntent().getExtras().getBoolean("user_view");
        }

        String[] fromKeyProperty = new String[] {"imageReply", "messageStatus", "address", "messageDate", "imageSend"};
        int[] toResourceId = new int[] {R.id.imageReply, R.id.messageStatus, R.id.address, R.id.messageDate, R.id.imageSend};

        List<HashMap<String, String>> messageList = new ArrayList<HashMap<String, String>>();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.getMessageThread(userId, doctorId);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> message = new HashMap<String, String>();
                String senderId = cursor.getString(0);
                if (senderId.compareTo(userId) == 0) {
                    message.put("imageSend", String.valueOf(R.drawable.avatarimage));
                } else {
                    message.put("imageReply", String.valueOf(R.drawable.avatarimage));
                }
                message.put("address", cursor.getString(1) + "\n" + cursor.getString(2)); //address
                message.put("messageStatus", cursor.getString(3)); //message content
//            doctor.put("isView", cursor.getString(3)); // is_view
                message.put("messageDate", cursor.getString(5));
                message.put("doctor_id", cursor.getString(6));
                messageList.add(message);
            }while (cursor.moveToNext());
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, messageList, R.layout.listview_message_content_item, fromKeyProperty, toResourceId);

        ListView listView = findViewById(R.id.lstMessageContent);
        listView.setAdapter(simpleAdapter);

//        listView.setOnItemClickListener((parent, view, position, id) -> {
//            String doctorId = doctorList.get(position).get("doctor_id");
//            Intent intent = new Intent(DoctorMessageActivity.this, MessageContentActivity.class);
//            intent.putExtra("sender", userId);
//            intent.putExtra("replier", doctorId);
//            intent.putExtra("user_view", true);
//        });

        calorieIcon = findViewById(R.id.imageViewFood);
        homeIcon = findViewById(R.id.imageViewHome);
        stethoscopeIcon = findViewById(R.id.imageViewStethoScope);

        //CONTEXTUAL MENU ICON FUNCTION
        calorieIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessageContentActivity.this, FoodTrackerActivity.class));
            }
        });
        //CONTEXTUAL MENU ICON FUNCTION
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessageContentActivity.this, UserMain.class));
            }
        });
        //CONTEXTUAL MENU ICON FUNCTION
        stethoscopeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessageContentActivity.this, FindDoctor.class));
            }
        });
    }

    public void goback(View view) {
        this.onBackPressed();
    }
}