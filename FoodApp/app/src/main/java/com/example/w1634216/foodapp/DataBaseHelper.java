//w1634216 Benjamin Simon
package com.example.w1634216.foodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{
    public static  final String DATABASE_NAME = "food.db";
    public static  final String TABLE_NAME = "food_table";
    public static  final String COL_1 = "ID";
    public static  final String COL_2 = "NAME";
    public static  final String COL_3 = "WEIGHT";
    public static  final String COL_4 = "PRICE";
    public static  final String COL_5 = "DESCRIPTION";
    public static  final String COL_6 = "AVAILABILITY";

    private static String[] From = {COL_1,COL_2,COL_3,COL_4,COL_5,COL_6};
    private static String Alpha = COL_2 + " COLLATE NOCASE ASC ";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    //Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, WEIGHT INTEGER, PRICE INTEGER, DESCRIPTION TEXT, AVAILABILITY TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Adds data to table
    public boolean insertData(String name, String weight, String price, String description, String available){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,weight);
        contentValues.put(COL_4,price);
        contentValues.put(COL_5,description);
        contentValues.put(COL_6,available);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    //Get name of food
    public Cursor getNameList(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return data;
    }

    //Checks if food is in kitchen
    public Cursor getEvents(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "Availability =?";
        String[] selectionArgs = {"Available"};

        Cursor cursor = db.query(TABLE_NAME, From,selection,selectionArgs, null,null,Alpha);
        return cursor;
    }
}
