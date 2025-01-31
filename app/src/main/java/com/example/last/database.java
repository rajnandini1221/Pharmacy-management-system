package com.example.last;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "PharmacyDB";

    // Table Names
    private static final String TABLE_CUSTOMERS = "customers";
    private static final String TABLE_MEDICINES = "medicines";
    private static final String TABLE_SUPPLIERS = "suppliers";
    private static final String TABLE_PURCHASES = "purchases";
    private static final String TABLE_SALES = "sales";
    private static final String TABLE_OUT_OF_STOCK = "out_of_stock";

    // Common Column Names
    private static final String KEY_ID = "id";

    // Customers Table Columns
    private static final String KEY_CUSTOMER_NAME = "customer_name";
    private static final String KEY_CUSTOMER_NUMBER = "customer_number";
    private static final String KEY_CUSTOMER_ADDRESS = "customer_address";
    private static final String KEY_DOCTOR_NAME = "doctor_name";

    // Medicines Table Columns
    private static final String KEY_SERIAL_NO = "serial_no";
    private static final String KEY_MEDICINE_NAME = "medicine_name";
    private static final String KEY_PACKING = "packing";
    private static final String KEY_GENERIC_NAME = "generic_name";
    private static final String KEY_SUPPLIER = "supplier";

    // Suppliers Table Columns
    private static final String KEY_SUPPLIER_NAME = "supplier_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CONTACT_NO = "contact_no";
    private static final String KEY_SUPPLIER_ADDRESS = "supplier_address";

    // Purchases Table Columns
    private static final String KEY_VOUCHER_NO = "voucher_no";
    private static final String KEY_INVOICE_NO = "invoice_no";
    private static final String KEY_PURCHASE_DATE = "purchase_date";
    private static final String KEY_TOTAL_AMOUNT = "total_amount";
    private static final String KEY_PAYMENT_STATUS = "payment_status";

    // Sales Table Columns
    private static final String KEY_SALE_DATE = "sale_date";

    // Out of Stock Table Columns
    private static final String KEY_BATCH_NO = "batch_no";

    // Create Table Statements
    private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE " + TABLE_CUSTOMERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_CUSTOMER_NAME + " TEXT,"
            + KEY_CUSTOMER_NUMBER + " TEXT,"
            + KEY_CUSTOMER_ADDRESS + " TEXT,"
            + KEY_DOCTOR_NAME + " TEXT"
            + ")";

    private static final String CREATE_TABLE_MEDICINES = "CREATE TABLE " + TABLE_MEDICINES + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_SERIAL_NO + " TEXT,"
            + KEY_MEDICINE_NAME + " TEXT,"
            + KEY_PACKING + " TEXT,"
            + KEY_GENERIC_NAME + " TEXT,"
            + KEY_SUPPLIER + " TEXT"
            + ")";

    private static final String CREATE_TABLE_SUPPLIERS = "CREATE TABLE " + TABLE_SUPPLIERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_SUPPLIER_NAME + " TEXT,"
            + KEY_EMAIL + " TEXT,"
            + KEY_CONTACT_NO + " TEXT,"
            + KEY_SUPPLIER_ADDRESS + " TEXT"
            + ")";

    private static final String CREATE_TABLE_PURCHASES = "CREATE TABLE " + TABLE_PURCHASES + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_VOUCHER_NO + " TEXT,"
            + KEY_INVOICE_NO + " TEXT,"
            + KEY_PURCHASE_DATE + " TEXT,"
            + KEY_TOTAL_AMOUNT + " TEXT,"
            + KEY_PAYMENT_STATUS + " TEXT"
            + ")";

    private static final String CREATE_TABLE_SALES = "CREATE TABLE " + TABLE_SALES + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_VOUCHER_NO + " TEXT,"
            + KEY_INVOICE_NO + " TEXT,"
            + KEY_SALE_DATE + " TEXT,"
            + KEY_TOTAL_AMOUNT + " TEXT,"
            + KEY_PAYMENT_STATUS + " TEXT"
            + ")";

    private static final String CREATE_TABLE_OUT_OF_STOCK = "CREATE TABLE " + TABLE_OUT_OF_STOCK + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_SERIAL_NO + " TEXT,"
            + KEY_MEDICINE_NAME + " TEXT,"
            + KEY_BATCH_NO + " TEXT,"
            + KEY_GENERIC_NAME + " TEXT,"
            + KEY_SUPPLIER + " TEXT"
            + ")";

    public database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating Tables
        db.execSQL(CREATE_TABLE_CUSTOMERS);
        db.execSQL(CREATE_TABLE_MEDICINES);
        db.execSQL(CREATE_TABLE_SUPPLIERS);
        db.execSQL(CREATE_TABLE_PURCHASES);
        db.execSQL(CREATE_TABLE_SALES);
        db.execSQL(CREATE_TABLE_OUT_OF_STOCK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUPPLIERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PURCHASES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OUT_OF_STOCK);

        // Create tables again
        onCreate(db);
    }

    // CRUD Operations for Customers
    public long addCustomer(String name, String number, String address, String doctorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CUSTOMER_NAME, name);
        values.put(KEY_CUSTOMER_NUMBER, number);
        values.put(KEY_CUSTOMER_ADDRESS, address);
        values.put(KEY_DOCTOR_NAME, doctorName);
        return db.insert(TABLE_CUSTOMERS, null, values);
    }

    public Cursor getAllCustomers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_CUSTOMERS, null, null, null, null, null, null);
    }

    // CRUD Operations for Medicines
    public long addMedicine(String serialNo, String medicineName, String packing, String genericName, String supplier) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SERIAL_NO, serialNo);
        values.put(KEY_MEDICINE_NAME, medicineName);
        values.put(KEY_PACKING, packing);
        values.put(KEY_GENERIC_NAME, genericName);
        values.put(KEY_SUPPLIER, supplier);
        return db.insert(TABLE_MEDICINES, null, values);
    }

    public Cursor getAllMedicines() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_MEDICINES, null, null, null, null, null, null);
    }

    // CRUD Operations for Suppliers
    public long addSupplier(String name, String email, String contactNo, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SUPPLIER_NAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_CONTACT_NO, contactNo);
        values.put(KEY_SUPPLIER_ADDRESS, address);
        return db.insert(TABLE_SUPPLIERS, null, values);
    }

    public Cursor getAllSuppliers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_SUPPLIERS, null, null, null, null, null, null);
    }

    // CRUD Operations for Purchases
    public long addPurchase(String voucherNo, String invoiceNo, String purchaseDate, String totalAmount, String paymentStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_VOUCHER_NO, voucherNo);
        values.put(KEY_INVOICE_NO, invoiceNo);
        values.put(KEY_PURCHASE_DATE, purchaseDate);
        values.put(KEY_TOTAL_AMOUNT, totalAmount);
        values.put(KEY_PAYMENT_STATUS, paymentStatus);
        return db.insert(TABLE_PURCHASES, null, values);
    }

    public Cursor getAllPurchases() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_PURCHASES, null, null, null, null, null, null);
    }

    // CRUD Operations for Sales
    public long addSale(String voucherNo, String invoiceNo, String saleDate, String totalAmount, String paymentStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_VOUCHER_NO, voucherNo);
        values.put(KEY_INVOICE_NO, invoiceNo);
        values.put(KEY_SALE_DATE, saleDate);
        values.put(KEY_TOTAL_AMOUNT, totalAmount);
        values.put(KEY_PAYMENT_STATUS, paymentStatus);
        return db.insert(TABLE_SALES, null, values);
    }

    public Cursor getAllSales() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_SALES, null, null, null, null, null, null);
    }

    // CRUD Operations for Out of Stock
    public long addOutOfStock(String serialNo, String medicineName, String batchNo, String genericName, String supplier) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SERIAL_NO, serialNo);
        values.put(KEY_MEDICINE_NAME, medicineName);
        values.put(KEY_BATCH_NO, batchNo);
        values.put(KEY_GENERIC_NAME, genericName);
        values.put(KEY_SUPPLIER, supplier);
        return db.insert(TABLE_OUT_OF_STOCK, null, values);
    }

    public Cursor getAllOutOfStock() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_OUT_OF_STOCK, null, null, null, null, null, null);
    }
}