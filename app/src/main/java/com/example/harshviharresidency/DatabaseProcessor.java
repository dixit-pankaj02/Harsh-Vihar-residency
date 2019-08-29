package com.example.harshviharresidency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseProcessor {
    private SQLiteDatabase mDatabase;
    private DatabaseHelper mSQLHelper;
    private Context mContext;
    public static final String TABLE_ResidentsLogin = "ResidentsLogin";
    public static final String TABLE_ResidentsData = "ResidentsData";
    public static final String TABLE_Expenses = "Expenses";
    public static final String TABLE_Collections = "Collections";

    public DatabaseProcessor(Context context) {
        mContext = context;
        mSQLHelper = new DatabaseHelper(mContext);
    }

    private void open() throws SQLException {
        mDatabase = mSQLHelper.getWritableDatabase();
    }

    private void close() {
        mDatabase.close();
    }

    public void registerUser(String userName, String Password) {
        ContentValues CV = new ContentValues();

        CV.put("username", userName);
        CV.put("password", Password);
        open();

        mDatabase.insert(TABLE_ResidentsLogin, null, CV);
        close();
    }

    public Cursor getUsers(String userName) {
        Cursor cursor = null;
        open();
        cursor = mDatabase.rawQuery("select * from " + TABLE_ResidentsLogin + " Where username='" + userName + "'", null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }
}
