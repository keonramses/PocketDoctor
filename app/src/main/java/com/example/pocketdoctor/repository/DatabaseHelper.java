package com.example.pocketdoctor.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.sql.DriverManager.println;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pocket_docter";
    private static final int DATABASE_VERSION = 1;
    private final String initDatabaseString;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        String databaseSqlString = "";
        try {
            InputStream inputStream = context.getAssets().open("sqlite_database_v1");
            int c;
            while ((c = inputStream.read()) != -1) {
                databaseSqlString = databaseSqlString + Character.toString((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.initDatabaseString = databaseSqlString;

        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String[] queries = this.initDatabaseString.split(";\r\n");
        for (String sql:
             queries) {
            db.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    public Cursor findUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT user_id FROM User WHERE email='" + username + "' AND password='" + password +"';";
        Cursor result = db.rawQuery(sql, null);
        return result;
    }

    public boolean insertData (String userFirstName, String userLastName, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", userFirstName);
        contentValues.put("last_name", userLastName);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = db.insert("User", null, contentValues);
        if(result > 0)
            return true;
        else
            return false;
    }


    public boolean addUserDailyIntake(String userId, int calories, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = 0;
        int todayCalories = getTotalUserDailyIntake(userId, date);
        if (todayCalories == -1) {
            // insert new
            ContentValues contentValues = new ContentValues();
            contentValues.put("user_id", userId);
            contentValues.put("created_date", date);
            contentValues.put("calories", calories);
            result = db.insert("UserDailyIntake", null, contentValues);
        } else {
            // update
            ContentValues contentValues = new ContentValues();
            contentValues.put("calories", calories);
            result = db.update("UserDailyIntake", contentValues, "user_id = ? and created_date = ?", new String[]{userId, date});
        }

        if(result > 0)
            return true;
        else
            return false;
    }

    public int getTotalUserDailyIntake(String userId, String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT calories FROM UserDailyIntake WHERE user_id = ? AND created_date = ? ";
        Cursor cursor = db.rawQuery(sql, new String[] {userId, date});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        } else {
            return -1;
        }
    }
}
