package com.example.last;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TotalCustomersActivity extends AppCompatActivity {

    private EditText customerNameEditText, customerNumberEditText, customerAddressEditText, doctorNameEditText;
    private Button submitButton;
    private ListView customerListView;
    private customerdatabase dbHelper;
    private ArrayList<String> customerList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totalcustomersactivity);

        // Initialize the DatabaseHelper
        dbHelper = new customerdatabase(this);

        // Initialize views
        customerNameEditText = findViewById(R.id.customer_name);
        customerNumberEditText = findViewById(R.id.customer_number);
        customerAddressEditText = findViewById(R.id.customer_address);
        doctorNameEditText = findViewById(R.id.doctor_name);
        submitButton = findViewById(R.id.done);
        customerListView = findViewById(R.id.customer_list);

        // Load customers from the database
        loadCustomers();

        // Set up the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = customerNameEditText.getText().toString();
                String number = customerNumberEditText.getText().toString();
                String address = customerAddressEditText.getText().toString();
                String doctor = doctorNameEditText.getText().toString();

                if (name.isEmpty() || number.isEmpty() || address.isEmpty() || doctor.isEmpty()) {
                    Toast.makeText(TotalCustomersActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Add customer to the database
                    dbHelper.addCustomer(name, number, address, doctor);
                    Toast.makeText(TotalCustomersActivity.this, "Customer added", Toast.LENGTH_SHORT).show();

                    // Clear input fields
                    customerNameEditText.setText("");
                    customerNumberEditText.setText("");
                    customerAddressEditText.setText("");
                    doctorNameEditText.setText("");

                    // Reload customer list
                    loadCustomers();
                }
            }
        });

        // Set up item click listener for ListView (to delete customer)
        customerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected customer and delete it
                String selectedCustomer = customerList.get(position);
                String[] customerDetails = selectedCustomer.split("\n");
                String customerName = customerDetails[0].split(":")[1].trim();
                dbHelper.deleteCustomerByName(customerName);
                Toast.makeText(TotalCustomersActivity.this, "Customer deleted", Toast.LENGTH_SHORT).show();

                // Reload customer list
                loadCustomers();
            }
        });
    }

    private void loadCustomers() {
        // Get all customers from the database
        customerList = dbHelper.getAllCustomers();
        // Set up the adapter for the ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, customerList);
        customerListView.setAdapter(adapter);
    }
}
