package com.example.last;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class outdatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PharmacyDB";
    private static final int DATABASE_VERSION = 1;

    public outdatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE OutOfStock (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "serial TEXT, " +
                "medicine TEXT, " +
                "batch TEXT, " +
                "generic TEXT, " +
                "supplier TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS OutOfStock");
        onCreate(db);
    }
}
