package com.example.pocketdoctor.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.pocketdoctor.EditAdminUserAccountActivity;
import com.example.pocketdoctor.PocketDoctorApplication;

import com.example.pocketdoctor.PocketDoctorApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.println;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pocket_docter";
    final int NORMAL_USER = 1;
    final int DOCTOR_USER = 2;
    final int CASHIER_USER = 3;
    final int ADMIN_USER = 4;
    private static final int DATABASE_VERSION = 1;
    private final String initDatabaseString;
    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        String databaseSqlString = "";
        try {
            InputStream inputStream = context.getAssets().open("sqlite_database_v1");
            int c;
            while ((c = inputStream.read()) != -1) {
                databaseSqlString = databaseSqlString + Character.toString((char) c);
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
        for (String sql :
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
        String sql = "SELECT user_id, user_type FROM User WHERE email='" + username + "' AND password='" + password + "';";
        Cursor result = db.rawQuery(sql, null);
        return result;
    }

    public boolean insertDoctorAndCashier(String userId, String userFirstName, String userLastName, String email, String password, String address,
                                          int user_type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = 0;
        if (!email.matches(emailPattern)) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", userId);
        contentValues.put("first_name", userFirstName);
        contentValues.put("last_name", userLastName);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("address", address);
        contentValues.put("user_type", user_type);
        result = db.insert("User", null, contentValues);
        if (result > 0)
            return true;
        else
            return false;
    }

    public boolean insertData(String userId, String userFirstName, String userLastName, String email, String password, String msp,
                              int user_type) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = 0;
        if (!email.matches(emailPattern)) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", userId);
        contentValues.put("first_name", userFirstName);
        contentValues.put("last_name", userLastName);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("msp", msp);
        contentValues.put("user_type", user_type);
        result = db.insert("User", null, contentValues);
        if (result > 0)
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

        if (result > 0)
            return true;
        else
            return false;
    }

    public int getTotalUserDailyIntake(String userId, String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT calories FROM UserDailyIntake WHERE user_id = ? AND created_date = ? ";
        Cursor cursor = db.rawQuery(sql, new String[]{userId, date});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        } else {
            return -1;
        }
    }
    public Cursor getUsersList(int user_type) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT user_id, first_name, last_name, email, msp  FROM User WHERE user_type='" + user_type + "';";
        Cursor data = sqLiteDatabase.rawQuery(query, null);
        return data;
    }

    public PocketDoctorApplication searchUser(String email, int userType) {
        PocketDoctorApplication contact = null;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        try {
            String query = "SELECT user_id, first_name, last_name, email, msp, address  FROM User WHERE email='" + email + "' AND user_type='" + userType + "';";
            Cursor data = sqLiteDatabase.rawQuery(query, null);
            if (data.moveToFirst()) {
                contact = new PocketDoctorApplication();
                do {
                    contact.setCurrentUserId(data.getString(0));
                    contact.setCurrentUserName(data.getString(1));
                    contact.setCurrentUserLastName(data.getString(2));
                    contact.setCurrentUserEmail(data.getString(3));
                    contact.setCurrentUserMSP(data.getString(4));
                    contact.setCurrentUserAddress(data.getString(5));
                } while (data.moveToNext());
            }
        } catch (Exception e) {
            contact = null;
        }
        return contact;
    }

    public boolean upDate(String id, String name, String lastName, String email, String msp) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = 0;
        if (!msp.equalsIgnoreCase("yes") && !msp.equalsIgnoreCase("no")) {
            return false;
        }
        if (!email.matches(emailPattern)) {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put("first_name", name);
        values.put("last_name", lastName);
        values.put("email", email);
        values.put("msp", msp);
        result = sqLiteDatabase.update("User", values, "user_id=?",
                new String[]{id});
        if (result > 0)
            return true;
        else
            return false;
    }
    public boolean upDateDoctorAndCashier(String id, String name, String lastName, String email, String address) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = 0;
        if (!email.matches(emailPattern)) {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put("first_name", name);
        values.put("last_name", lastName);
        values.put("email", email);
        values.put("address", address);
        result = sqLiteDatabase.update("User", values, "user_id=?",
                new String[]{id});
        if (result > 0)
            return true;
        else
            return false;
    }


    public Cursor getDoctorNearYou()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT user_id, first_name, last_name, address FROM User WHERE user_type = 2";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public Cursor findDoctor(String docId)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT first_name, last_name, address FROM User WHERE user_id = ?";
        Cursor cursor = db.rawQuery(sql, new String[] {docId});
        return cursor;
    }

    public boolean insertDoctorAppointment(String userId, String doctorId, String date, String message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("patient_id", userId);
        values.put("doctor_id", doctorId);
        values.put("content", message);
        values.put("created_date", date);
        values.put("is_view", 0);
        try {
            db.insertOrThrow("PatientMessage", null, values);
        } catch (SQLiteConstraintException ex) {
            return false;
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
