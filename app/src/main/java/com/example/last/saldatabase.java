package com.example.last;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class saldatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sales.db";
    private static final int DATABASE_VERSION = 1;

    public saldatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE sales (id INTEGER PRIMARY KEY AUTOINCREMENT, invoice TEXT, date TEXT, amount TEXT, status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS sales");
        onCreate(db);
    }

    public void insertSale(String invoice, String date, String amount, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO sales (invoice, date, amount, status) VALUES (?, ?, ?, ?)",
                new Object[]{invoice, date, amount, status});
    }

    public Cursor getAllSales() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM sales", null);
    }
}
