package com.example.last;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class customerdatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CustomersDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CUSTOMERS = "customers";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_DOCTOR = "doctor";

    public customerdatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_NUMBER + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_DOCTOR + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        onCreate(db);
    }

    // Insert Data
    public void addCustomer(String name, String number, String address, String doctor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_NUMBER, number);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_DOCTOR, doctor);
        db.insert(TABLE_CUSTOMERS, null, values);
        db.close();
    }

    // Get All Customers
    public ArrayList<String> getAllCustomers() {
        ArrayList<String> customerList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CUSTOMERS, null);

        if (cursor.moveToFirst()) {
            do {
                String customer = "ID: " + cursor.getInt(0) + "\n" +
                        "Name: " + cursor.getString(1) + "\n" +
                        "Number: " + cursor.getString(2) + "\n" +
                        "Address: " + cursor.getString(3) + "\n" +
                        "Doctor: " + cursor.getString(4);
                customerList.add(customer);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return customerList;
    }

    // Delete Customer by ID
    public void deleteCustomer(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CUSTOMERS, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteCustomerByName(String customerName) {
    }
}
