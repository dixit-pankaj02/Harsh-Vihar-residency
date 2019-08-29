package com.example.harshviharresidency;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper mInstance = null;
    public static final String DATABASE_NAME = "DBHarshViharSociety.db";
    public static final String TABLE_ResidentsLogin = "ResidentsLogin";
    public static final String TABLE_ResidentsData = "ResidentsData";
    public static final String TABLE_Expenses = "Expenses";
    public static final String TABLE_Collections = "Collections";
    private static final int DB_VERSION = 1;

    private Context mContext;

    public static DatabaseHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_ResidentsLogin + " " +
                "(id integer Primary key AutoIncrement, " +
                "username varchar(150)," +
                "password varchar(50))");

        db.execSQL("create table " + TABLE_ResidentsData +
                "(id integer Primary key AutoIncrement, " +
                "firstname varchar(150)," +
                "Lastname varchar(150)," +
                "houseNumber varchar(150)," +
                "MobileNumber varchar(50)," +
                "EmailId varchar(150)," +
                "familyMember varchar(150))");

        db.execSQL("create table " + TABLE_Expenses +
                "(id integer Primary key AutoIncrement, " +
                "BillerName varchar(150)," +
                "ExpenseType varchar(50)," +
                "PaymentMode varchar(50)," +
                "TransactionNumber varchar(50)," +
                "Amount decimal(18,2)," +
                "Date date)");

        db.execSQL("create table " + TABLE_Collections + " " +
                "(id integer Primary key AutoIncrement, " +
                "Name varchar(150)," +
                "HouseNumber varchar(50)," +
                "CollectionType varchar(50)," +
                "PaymentMode varchar(50)," +
                "TransactionNumber varchar(50)," +
                "Amount decimal(18,2)," +
                "Date date," +
                "Month varchar(50)," +
                "Year integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
