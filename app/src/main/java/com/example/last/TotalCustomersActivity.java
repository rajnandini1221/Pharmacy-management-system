package com.example.last;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TotalCustomersActivity extends AppCompatActivity {

    // Declare the EditText fields
    private EditText customerNameEditText;
    private EditText customerNumberEditText;
    private EditText customerAddressEditText;
    private EditText doctorNameEditText;

    // Declare the Button to handle form submission
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totalcustomersactivity);

        // Initialize the EditText fields from the layout
        customerNameEditText = findViewById(R.id.customer_name);
        customerNumberEditText = findViewById(R.id.customer_number);
        customerAddressEditText = findViewById(R.id.customer_address);
        doctorNameEditText = findViewById(R.id.doctor_name);

        // Initialize the Submit Button
        submitButton = findViewById(R.id.done);

        // Set up an onClickListener for the Submit Button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Capture the input from the EditText fields
                String customerName = customerNameEditText.getText().toString();
                String customerNumber = customerNumberEditText.getText().toString();
                String customerAddress = customerAddressEditText.getText().toString();
                String doctorName = doctorNameEditText.getText().toString();

                // Check if all fields are filled
                if (customerName.isEmpty() || customerNumber.isEmpty() || customerAddress.isEmpty() || doctorName.isEmpty()) {
                    Toast.makeText(TotalCustomersActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Here, you can process the data as needed, e.g., store it in a database or send it to a server
                    // For demonstration, let's show a Toast message with the entered data
                    String customerDetails = "Name: " + customerName + "\n" +
                            "Number: " + customerNumber + "\n" +
                            "Address: " + customerAddress + "\n" +
                            "Doctor: " + doctorName;

                    // Display the customer details as a Toast message
                    Toast.makeText(TotalCustomersActivity.this, customerDetails, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

