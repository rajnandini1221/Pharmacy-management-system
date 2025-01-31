package com.example.last;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class OutOfStockActivity extends AppCompatActivity {

    private EditText searchBar, serialNo, medicineName, batchNo, genericName, supplier;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outofstockactivity);

        // Initialize UI components
        searchBar = findViewById(R.id.search_bar);
        serialNo = findViewById(R.id.srlno);
        medicineName = findViewById(R.id.medinamo);
        batchNo = findViewById(R.id.outbtch);
        genericName = findViewById(R.id.generic);
        supplier = findViewById(R.id.medsupp);
        submitButton = findViewById(R.id.done);

        // Set up button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmit();
            }
        });
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
            // Perform database operation or API call here
            Toast.makeText(this, "Data Submitted Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
