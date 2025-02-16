package com.example.last;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;

public class SaleReportActivity extends AppCompatActivity {
    private EditText invoiceNumber, saleDate, totalAmount;
    private RadioGroup paymentStatusGroup;
    private Button submitButton;
    private ListView saleListView;
    private DatabaseHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> saleRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salereportactivity);

        // Initialize UI components
        invoiceNumber = findViewById(R.id.innno);
        saleDate = findViewById(R.id.saled);
        totalAmount = findViewById(R.id.totlamt);
        paymentStatusGroup = findViewById(R.id.paymentStatusGroup);
        submitButton = findViewById(R.id.done);
        saleListView = findViewById(R.id.saleListView);
        dbHelper = new DatabaseHelper(this);

        saleRecords = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, saleRecords);
        saleListView.setAdapter(adapter);

        saleDate.setOnClickListener(v -> openDatePicker());
        submitButton.setOnClickListener(v -> submitSaleEntry());

        loadSaleRecords();
    }

    private void openDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            saleDate.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1);
        }, year, month, day);
        datePickerDialog.show();
    }

    private void submitSaleEntry() {
        String invoice = invoiceNumber.getText().toString().trim();
        String saleDt = saleDate.getText().toString().trim();
        String totalAmt = totalAmount.getText().toString().trim();
        int selectedPaymentId = paymentStatusGroup.getCheckedRadioButtonId();
        String paymentStatus = (selectedPaymentId == R.id.paymentDone) ? "Done" : "Pending";

        if (invoice.isEmpty() || saleDt.isEmpty() || totalAmt.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        dbHelper.insertSale(invoice, saleDt, totalAmt, paymentStatus);
        loadSaleRecords();
        Toast.makeText(this, "Sale entry submitted successfully", Toast.LENGTH_SHORT).show();
    }

    private void loadSaleRecords() {
        saleRecords.clear();
        Cursor cursor = dbHelper.getAllSales();
        while (cursor.moveToNext()) {
            String record = "Invoice: " + cursor.getString(1) + " | Date: " + cursor.getString(2) +
                    " | Amount: " + cursor.getString(3) + " | Status: " + cursor.getString(4);
            saleRecords.add(record);
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "sales.db";
        private static final int DATABASE_VERSION = 1;

        DatabaseHelper(SaleReportActivity context) {
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

        void insertSale(String invoice, String date, String amount, String status) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("INSERT INTO sales (invoice, date, amount, status) VALUES (?, ?, ?, ?)",
                    new Object[]{invoice, date, amount, status});
        }

        Cursor getAllSales() {
            SQLiteDatabase db = this.getReadableDatabase();
            return db.rawQuery("SELECT * FROM sales", null);
        }
    }
}
