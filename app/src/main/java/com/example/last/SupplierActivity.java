package com.example.last;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SupplierActivity extends AppCompatActivity {

    private EditText searchBar, supplierId, supplierName, email, contactNo, supplierAddress;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicineactivity);

        // Initialize UI elements
        searchBar = findViewById(R.id.search_bar);
        supplierId = findViewById(R.id.id);
        supplierName = findViewById(R.id.supname);
        email = findViewById(R.id.eml);
        contactNo = findViewById(R.id.cntno);
        supplierAddress = findViewById(R.id.suppaddress);
        submitButton = findViewById(R.id.done);

        // Set onClickListener for the Submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmit();
            }
        });
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

        // Process the data (e.g., store in database or send to server)
        Toast.makeText(this, "Supplier details submitted successfully!", Toast.LENGTH_LONG).show();
    }
}
