package com.example.last;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class SupplierActivity extends AppCompatActivity {

    private EditText supplierId, supplierName, email, contactNo, supplierAddress;
    private Button submitButton;
    private ListView supplierListView;
    private SupplierDatabaseHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> supplierList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supplieractivity);

        // Initialize UI elements
        supplierId = findViewById(R.id.id);
        supplierName = findViewById(R.id.supname);
        email = findViewById(R.id.eml);
        contactNo = findViewById(R.id.cntno);
        supplierAddress = findViewById(R.id.suppaddress);
        submitButton = findViewById(R.id.done);
        supplierListView = findViewById(R.id.supplier_list);

        // Initialize Database Helper
        dbHelper = new SupplierDatabaseHelper(this);

        // Load Supplier Data
        supplierList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, supplierList);
        supplierListView.setAdapter(adapter);
        loadSupplierData();

        // Submit Button Click Listener
        submitButton.setOnClickListener(v -> handleSubmit());
    }

    private void handleSubmit() {
        String id = supplierId.getText().toString().trim();
        String name = supplierName.getText().toString().trim();
        String emailText = email.getText().toString().trim();
        String contact = contactNo.getText().toString().trim();
        String address = supplierAddress.getText().toString().trim();

        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(name) || TextUtils.isEmpty(emailText) ||
                TextUtils.isEmpty(contact) || TextUtils.isEmpty(address)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!contact.matches("\\d{10}")) {
            Toast.makeText(this, "Contact number must be 10 digits", Toast.LENGTH_SHORT).show();
            return;
        }

        dbHelper.insertSupplier(id, name, emailText, contact, address);
        Toast.makeText(this, "Supplier added successfully!", Toast.LENGTH_LONG).show();

        loadSupplierData();
    }

    private void loadSupplierData() {
        supplierList.clear();
        Cursor cursor = dbHelper.getAllSuppliers();

        if (cursor.moveToFirst()) {
            do {
                String data = "ID: " + cursor.getString(0) + "\n"
                        + "Name: " + cursor.getString(1) + "\n"
                        + "Email: " + cursor.getString(2) + "\n"
                        + "Contact: " + cursor.getString(3) + "\n"
                        + "Address: " + cursor.getString(4);
                supplierList.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }
}
