package com.example.last;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class purdatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PurchaseReportsDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_REPORTS = "purchase_reports";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_VOUCHER = "voucher";
    private static final String COLUMN_INVOICE = "invoice";
    private static final String COLUMN_PURCHASE_DATE = "purchase_date";
    private static final String COLUMN_TOTAL_AMOUNT = "total_amount";
    private static final String COLUMN_PAYMENT_STATUS = "payment_status";

    public purdatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_REPORTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_VOUCHER + " TEXT, " +
                COLUMN_INVOICE + " TEXT, " +
                COLUMN_PURCHASE_DATE + " TEXT, " +
                COLUMN_TOTAL_AMOUNT + " TEXT, " +
                COLUMN_PAYMENT_STATUS + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORTS);
        onCreate(db);
    }

    // Add a new report to the database
    public void addPurchaseReport(String voucher, String invoice, String purchaseDate, String totalAmount, String paymentStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VOUCHER, voucher);
        values.put(COLUMN_INVOICE, invoice);
        values.put(COLUMN_PURCHASE_DATE, purchaseDate);
        values.put(COLUMN_TOTAL_AMOUNT, totalAmount);
        values.put(COLUMN_PAYMENT_STATUS, paymentStatus);
        db.insert(TABLE_REPORTS, null, values);
        db.close();
    }

    // Retrieve all purchase reports
    public ArrayList<String> getAllReports() {
        ArrayList<String> reports = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_REPORTS, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String voucher = cursor.getString(cursor.getColumnIndex(COLUMN_VOUCHER));
                String invoice = cursor.getString(cursor.getColumnIndex(COLUMN_INVOICE));
                String purchaseDate = cursor.getString(cursor.getColumnIndex(COLUMN_PURCHASE_DATE));
                String totalAmount = cursor.getString(cursor.getColumnIndex(COLUMN_TOTAL_AMOUNT));
                String paymentStatus = cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENT_STATUS));
                reports.add("Voucher: " + voucher + "\n" + "Invoice: " + invoice + "\n" + "Purchase Date: " + purchaseDate + "\n" +
                        "Total Amount: " + totalAmount + "\n" + "Payment Status: " + paymentStatus);
            }
            cursor.close();
        }
        return reports;
    }

    // Delete report by voucher
    public void deleteReportByVoucher(String voucher) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REPORTS, COLUMN_VOUCHER + " = ?", new String[]{voucher});
        db.close();
    }
}
