package com.example.harshviharresidency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DBHarshViharSociety.db";
    public static final String TABLE_ResidentsLogin = "ResidentsLogin";
    public static final String TABLE_ResidentsData = "ResidentsData";
    public static final String TABLE_Expenses = "Expenses";
    public static final String TABLE_Collections = "Collections";
    SQLiteDatabase DB;

    public SqlDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_ResidentsLogin + " " +
                "(id integer Primary key AutoIncrement, " +
                "username varchar(150)," +
                "password varchar(50))");

//        db.execSQL("create table " + TABLE_ResidentsData +
//                "(id integer Primary key AutoIncrement, " +
//                "firstname varchar(150)," +
//                "Lastname varchar(150)," +
//                "houseNumber varchar(150)," +
//                "MobileNumber varchar(50)," +
//                "EmailId varchar(150)," +
//                "familyMember varchar(150))");
//
//        db.execSQL("create table " + TABLE_Expenses +
//                "(id integer Primary key AutoIncrement, " +
//                "BillerName varchar(150)," +
//                "ExpenseType varchar(50)," +
//                "PaymentMode varchar(50)," +
//                "TransactionNumber varchar(50)," +
//                "Amount decimal(18,2)," +
//                "Date date)");
//
//        db.execSQL("create table " + TABLE_Collections + " " +
//                "(id integer Primary key AutoIncrement, " +
//                "Name varchar(150)," +
//                "HouseNumber varchar(50)," +
//                "CollectionType varchar(50)," +
//                "PaymentMode varchar(50)," +
//                "TransactionNumber varchar(50)," +
//                "Amount decimal(18,2)," +
//                "Date date," +
//                "Month varchar(50)," +
//                "Year integer)");

        DB = this.getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void registerUser(String userName, String Password) {
        if (DB == null) {
            DB = this.getWritableDatabase();
        }

        ContentValues CV = new ContentValues();

        CV.put("username", userName);
        CV.put("password", Password);

        DB.insert(TABLE_ResidentsLogin, null, CV);
    }

    public Cursor showUsers() {
        Cursor cursor = null;

        DB = this.getReadableDatabase();

        cursor = DB.rawQuery("select * from " + TABLE_ResidentsLogin, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public Cursor getUsers(String userName) {
        Cursor cursor = null;

        DB = this.getReadableDatabase();

        cursor = DB.rawQuery("select * from " + TABLE_ResidentsLogin + " Where username=" + userName, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public void deleteUser(String id) {
        DB.delete(TABLE_ResidentsLogin, "id = " + id, null);
    }

    public void updateUser(String MObile, String Name) {
        DB = this.getWritableDatabase();

        ContentValues CV = new ContentValues();

        CV.put("name", Name);

        DB.update(TABLE_ResidentsLogin, CV, "mobile" + "=" + MObile, null);
    }
}
