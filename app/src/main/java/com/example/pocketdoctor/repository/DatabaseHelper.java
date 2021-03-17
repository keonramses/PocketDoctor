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
//        this.initDatabaseString = initDatabaseString;
//        context.getAssets().
        String temp = "";
        try {
            InputStream inputStream = context.getAssets().open("sqlite_database_v1");
            int c;
            while ((c = inputStream.read()) != -1) {
                temp = temp + Character.toString((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        this.initDatabaseString = temp;

        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String[] queries = this.initDatabaseString.split(";\r\n");
        for (String sql:
             queries) {
//            Log.println(Log.INFO,"DEBUG", sql);
//            db.execSQL(sql.replace("\r\n", " "));
            db.execSQL(sql);
        }
//        db.execSQL(this.initDatabaseString);
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

    public boolean findUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT user_id FROM User WHERE email='" + username + "' AND password='" + password +"';";
        Cursor result = db.rawQuery(sql, null);
        return result.getCount() > 0;
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


}
