package com.example.last;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SupplierDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "suppliers.db";
    private static final int DATABASE_VERSION = 1;

    public SupplierDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE suppliers (id TEXT PRIMARY KEY, name TEXT, email TEXT, contact TEXT, address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS suppliers");
        onCreate(db);
    }

    public void insertSupplier(String id, String name, String email, String contact, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("email", email);
        values.put("contact", contact);
        values.put("address", address);
        db.insert("suppliers", null, values);
    }

    public Cursor getAllSuppliers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM suppliers", null);
    }
}
