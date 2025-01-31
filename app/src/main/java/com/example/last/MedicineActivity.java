package com.example.last;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MedicineActivity extends AppCompatActivity {

    private EditText searchBar, serialNoEditText, medicineNameEditText, packingEditText,
            genericNameEditText, supplierEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicineactivity); // Ensure the correct layout is set

        // Initialize EditText fields
        searchBar = findViewById(R.id.search_bar);
        serialNoEditText = findViewById(R.id.customer_name);
        medicineNameEditText = findViewById(R.id.medinam);
        packingEditText = findViewById(R.id.pack);
        genericNameEditText = findViewById(R.id.generic);
        supplierEditText = findViewById(R.id.medsupp);

        // Initialize Button
        submitButton = findViewById(R.id.done);

        // Set up an onClickListener for the Submit Button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Capture the input from the EditText fields
                String serialNo = serialNoEditText.getText().toString().trim();
                String medicineName = medicineNameEditText.getText().toString().trim();
                String packing = packingEditText.getText().toString().trim();
                String genericName = genericNameEditText.getText().toString().trim();
                String supplier = supplierEditText.getText().toString().trim();

                // Check if all fields are filled
                if (serialNo.isEmpty() || medicineName.isEmpty() || packing.isEmpty() ||
                        genericName.isEmpty() || supplier.isEmpty()) {
                    Toast.makeText(MedicineActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Here, you can process the data (store it in a database, send to a server, etc.)
                    String medicineDetails = "Serial No: " + serialNo + "\n" +
                            "Medicine Name: " + medicineName + "\n" +
                            "Packing: " + packing + "\n" +
                            "Generic Name: " + genericName + "\n" +
                            "Supplier: " + supplier;

                    // Display the medicine details as a Toast message
                    Toast.makeText(MedicineActivity.this, "Medicine Added:\n" + medicineDetails, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

