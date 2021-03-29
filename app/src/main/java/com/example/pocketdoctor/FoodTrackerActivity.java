package com.example.pocketdoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.pocketdoctor.repository.DatabaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FoodTrackerActivity extends AppCompatActivity {

    private static final int SAFE_CALORIES_INTAKE = 2000;
    private static final int ALERT_CALORIES_INTAKE = 2600;
    ListView listView;
    SimpleAdapter simpleAdapter;
    String[] fromKeyProperty;
    int[] toResourceId;
    TextView txtTodayCalories;
    String userId;
    String today;
    int todayCalories;
    SearchView searchView;
    ImageView homeIcon;
    ImageView stethoscopeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_tracker);
        searchView = (SearchView)findViewById(R.id.edtSearchFood);
        searchView.onActionViewExpanded();
        homeIcon = findViewById(R.id.imageViewHome);
        stethoscopeIcon = findViewById(R.id.imageViewStethoScope);

        userId = ((PocketDoctorApplication)getApplication()).getCurrentUserId();
        //userId = "7d1bb54a-533b-42db-897d-a6cff78c89a7";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        today = simpleDateFormat.format(new Date());

        DatabaseHelper databaseHelper = new DatabaseHelper(FoodTrackerActivity.this);
        todayCalories = databaseHelper.getTotalUserDailyIntake(userId, today);

        txtTodayCalories = (TextView)findViewById(R.id.txtTodayCalories);

        txtTodayCalories.setText(String.valueOf(todayCalories > 0 ? todayCalories : 0));
        initListFoodItems();


    }

    private void initListFoodItems() {
        String[] foodNames = new String[]{"50gr Pasta cabonara ", "1 Eggs", "1 cup fruit salad", "3 slices Bacon", "1 Coke"};
        int[] foodCalories = new int[]{1000, 200, 500, 25, 100};

        // Init resource for list view adapter
        fromKeyProperty = new String[] {"name", "calories"};
        toResourceId = new int[] {R.id.lstFoodItemName, R.id.lstFoodItemCalories};

        List<HashMap<String, String>> foodList = new ArrayList<>();

        for (int i = 0; i < foodCalories.length; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put(fromKeyProperty[0], foodNames[i]);
            item.put(fromKeyProperty[1], String.valueOf(foodCalories[i]));
            foodList.add(item);
        }

        listView = findViewById(R.id.lstDailyFood);
        simpleAdapter = new SimpleAdapter(this, foodList, R.layout.listview_daily_food_item, fromKeyProperty, toResourceId);
        // getlist food from database
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            int calories = foodCalories[position];
            DatabaseHelper databaseHelper = new DatabaseHelper(FoodTrackerActivity.this);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
            String today = simpleDateFormat.format(new Date());

            if (todayCalories > 0) {
                calories += todayCalories;
            }

            todayCalories = calories;

            databaseHelper.addUserDailyIntake(userId, calories, today);

            txtTodayCalories.setText(String.valueOf(calories) + "\n" + "Calories");
            if (calories <= SAFE_CALORIES_INTAKE) {
                txtTodayCalories.setBackgroundTintList(ContextCompat.getColorStateList(FoodTrackerActivity.this, R.color.green));
            } else if (calories > SAFE_CALORIES_INTAKE && calories <= ALERT_CALORIES_INTAKE) {
                txtTodayCalories.setBackgroundTintList(ContextCompat.getColorStateList(FoodTrackerActivity.this, R.color.yellow));
            } else {
                txtTodayCalories.setBackgroundTintList(ContextCompat.getColorStateList(FoodTrackerActivity.this, R.color.orange));
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                simpleAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                simpleAdapter.getFilter().filter(newText);
                return false;
            }
        });

        //CONTEXTUAL MENU ICON FUNCTION
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodTrackerActivity.this, UserMain.class));
            }
        });

        //CONTEXTUAL MENU ICON FUNCTION
        stethoscopeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodTrackerActivity.this, FindDoctor.class));
            }
        });
    }

    public void gotoProfileActivity (View view) {
        startActivity(new Intent(FoodTrackerActivity.this, UserProfile.class));
    }
    public void gotoLoginActivity(View view){
        startActivity(new Intent(FoodTrackerActivity.this, LoginActivity.class));
    }
}