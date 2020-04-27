package com.example.justjava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class CoffeeDBHandler extends SQLiteOpenHelper {
    // attributes
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "coffeebase.db";
    public static final String TABLE_COFFEE = "coffeesales";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CUSTOMERNAME = "customername";
    public static final String COLUMN_SALEAMOUNT = "salesamount";

    // constructor
    public CoffeeDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    // onCreate methods; spaces are important in the query!
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_COFFEE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CUSTOMERNAME + " TEXT, " +
                COLUMN_SALEAMOUNT + " INTEGER " +
                ");";
        db.execSQL(query);
    } // the highlights elements are part of the database

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_COFFEE);
    onCreate(db);
    } // do not forget the spaces!

    public void addOrder(Order order){
        ContentValues values = new ContentValues();
        values.put(COLUMN_CUSTOMERNAME, order.get_custName());
        values.put(COLUMN_SALEAMOUNT, order.get_saleAmount());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_COFFEE, null, values);
        db.close();
    }

    public String databaseToString(){
        String dbString = ""; // can just say null
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_COFFEE + " WHERE 1"; // spaces!
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        // creating loop to read and store data in string; ! = "not"
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_CUSTOMERNAME))!=null){ // means "keep going under there is no customer name"
                dbString += c.getString(c.getColumnIndex(COLUMN_CUSTOMERNAME)) + " --> $ " +
                            c.getString(c.getColumnIndex(COLUMN_SALEAMOUNT));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}
