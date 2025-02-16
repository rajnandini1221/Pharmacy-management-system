package com.example.last;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class MedicineDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MedicineDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MEDICINE = "medicines";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SERIAL_NO = "serial_no";
    private static final String COLUMN_NAME = "medicine_name";
    private static final String COLUMN_PACKING = "packing";
    private static final String COLUMN_GENERIC = "generic_name";
    private static final String COLUMN_SUPPLIER = "supplier";

    public MedicineDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_MEDICINE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SERIAL_NO + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PACKING + " TEXT, " +
                COLUMN_GENERIC + " TEXT, " +
                COLUMN_SUPPLIER + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINE);
        onCreate(db);
    }

    public void addMedicine(String serialNo, String medicineName, String packing, String genericName, String supplier) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SERIAL_NO, serialNo);
        values.put(COLUMN_NAME, medicineName);
        values.put(COLUMN_PACKING, packing);
        values.put(COLUMN_GENERIC, genericName);
        values.put(COLUMN_SUPPLIER, supplier);

        db.insert(TABLE_MEDICINE, null, values);
        db.close();
    }

    public List<String> getAllMedicines() {
        List<String> medicineList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MEDICINE, null);
        if (cursor.moveToFirst()) {
            do {
                String record = "Serial No: " + cursor.getString(1) + "\n" +
                        "Medicine Name: " + cursor.getString(2) + "\n" +
                        "Packing: " + cursor.getString(3) + "\n" +
                        "Generic Name: " + cursor.getString(4) + "\n" +
                        "Supplier: " + cursor.getString(5);
                medicineList.add(record);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return medicineList;
    }
}
