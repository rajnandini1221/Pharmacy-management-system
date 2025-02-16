package com.example.last;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class OutOfStockActivity extends AppCompatActivity {

    private EditText serialNo, medicineName, batchNo, genericName, supplier;
    private Button submitButton;
    private ListView outOfStockListView;
    private outdatabase databaseHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outofstockactivity);

        serialNo = findViewById(R.id.srlno);
        medicineName = findViewById(R.id.medinamo);
        batchNo = findViewById(R.id.outbtch);
        genericName = findViewById(R.id.generic);
        supplier = findViewById(R.id.medsupp);
        submitButton = findViewById(R.id.done);
        outOfStockListView = findViewById(R.id.outOfStockListView);

        // Initialize database
        databaseHelper = new outdatabase(this);
        database = databaseHelper.getWritableDatabase();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmit();
            }
        });

        displayRecords();
    }

    private void handleSubmit() {
        String serial = serialNo.getText().toString().trim();
        String medicine = medicineName.getText().toString().trim();
        String batch = batchNo.getText().toString().trim();
        String generic = genericName.getText().toString().trim();
        String supplierName = supplier.getText().toString().trim();

        if (serial.isEmpty() || medicine.isEmpty() || batch.isEmpty() || generic.isEmpty() || supplierName.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            database.execSQL("INSERT INTO OutOfStock (serial, medicine, batch, generic, supplier) VALUES (?, ?, ?, ?, ?)",
                    new String[]{serial, medicine, batch, generic, supplierName});
            Toast.makeText(this, "Data Submitted Successfully", Toast.LENGTH_SHORT).show();
            clearFields();
            displayRecords();
        }
    }

    private void clearFields() {
        serialNo.setText("");
        medicineName.setText("");
        batchNo.setText("");
        genericName.setText("");
        supplier.setText("");
    }

    private void displayRecords() {
        Cursor cursor = database.rawQuery("SELECT * FROM OutOfStock", null);
        String[] fromColumns = {"serial", "medicine"};
        int[] toViews = {android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, cursor,
                fromColumns, toViews, 0);
        outOfStockListView.setAdapter(adapter);
    }
}
