package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pocketdoctor.repository.DatabaseHelper;

import java.util.ArrayList;

public class ListOfDoctors extends AppCompatActivity {

    String stCity;
    ListView listView;
    DatabaseHelper databaseHelper;
    ArrayList<User> arrayList;
    MyAdapter myAdapter;
    ImageView calorieIcon;
    ImageView homeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_doctors);

        listView = findViewById(R.id.listView);
        stCity = "";
        if (getIntent().getExtras() != null) {
            stCity = getIntent().getExtras().getString("ValueCity");
        }
        arrayList = new ArrayList<User>();

//        this.deleteDatabase("pocket_docter"); // Delete and recreate database
        databaseHelper = new DatabaseHelper(this);

        arrayList = GetDoctors();
        loadDataInListViewII(arrayList);

        calorieIcon = findViewById(R.id.imageViewFood);
        homeIcon = findViewById(R.id.imageViewHome);

        //CONTEXTUAL MENU ICON FUNCTION
        calorieIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListOfDoctors.this, FoodTrackerActivity.class));
            }
        });

        //CONTEXTUAL MENU ICON FUNCTION
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListOfDoctors.this, UserMain.class));
            }
        });

    }
    private ArrayList<User> GetDoctors(){
        Cursor cursor = databaseHelper.getDoctorNearYou();
        ArrayList<User> arrayList = new ArrayList<>();
        while(cursor.moveToNext()) {
            String doctorId = cursor.getString(0);
            String firstName = cursor.getString(1);
            String lastName = cursor.getString(2);
            String address = cursor.getString(3);
            User user;
            if((!stCity.isEmpty()) && (address.toLowerCase().indexOf(stCity.toLowerCase()) != -1)) {
                user = new User(doctorId, firstName, lastName, address);
                arrayList.add(user);
            }else if(stCity.isEmpty()){
                user = new User(doctorId, firstName, lastName, address);
                arrayList.add(user);
            }
                // if city has been entered, show the match result
           /* if(!stCity.isEmpty() && address.toLowerCase().indexOf(stCity.toLowerCase()) != -1){
                User user = new User(doctorId, firstName, lastName, address);
                arrayList.add(user);
            }else{
                // show all
                User user = new User(doctorId, firstName, lastName, address);
                arrayList.add(user);
            }*/

        }
        return arrayList;
    }

        private void loadDataInListViewII(ArrayList<User> arrayListDoctor) {
        arrayList = arrayListDoctor;
        myAdapter = new MyAdapter(this,arrayList);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }


    public void goToBookAppointmentHandler(View v)
    {

        LinearLayout vParenRow = (LinearLayout)v.getParent();
        TextView child = (TextView)vParenRow.getChildAt(1);
       Intent i = new Intent(ListOfDoctors.this, Booking.class);
       i.putExtra("textValue",child.getText().toString());
        startActivity(i);
    }

    public void gotoFindDoctor(View view) {
        startActivity(new Intent(ListOfDoctors.this, FindDoctor.class));
    }
    public void gotoLoginActivity(View view){
        startActivity(new Intent(ListOfDoctors.this, LoginActivity.class));
    }
}