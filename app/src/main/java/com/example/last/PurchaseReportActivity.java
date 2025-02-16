package com.example.last;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class PurchaseReportActivity extends AppCompatActivity {

    private EditText vouEditText, invoEditText, purcEditText, totalEditText;
    private RadioGroup paymentStatusGroup;
    private RadioButton paymentPending, paymentDone;
    private Button submitButton;
    private ListView reportListView;
    private purdatabase dbHelper;
    private ArrayList<String> reportList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchasereportactivity); // Ensure layout name is correct

        // Initialize the views
        vouEditText = findViewById(R.id.vouc);
        invoEditText = findViewById(R.id.innno);
        purcEditText = findViewById(R.id.purchd);
        totalEditText = findViewById(R.id.totlamt);
        paymentStatusGroup = findViewById(R.id.paymentStatusGroup);
        paymentPending = findViewById(R.id.paymentPending);
        paymentDone = findViewById(R.id.paymentDone);
        submitButton = findViewById(R.id.done);
        reportListView = findViewById(R.id.customer_list);

        // Initialize database helper
        dbHelper = new purdatabase(this);

        // Load saved data
        loadReports();

        // Set a listener for the purchase date field
        purcEditText.setOnClickListener(v -> showDatePicker());

        // Set listener for submit button
        submitButton.setOnClickListener(v -> submitPaymentDetails());

        // Set listener for ListView item click (delete the item)
        reportListView.setOnItemClickListener(this::onItemClick);
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(PurchaseReportActivity.this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    purcEditText.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
                }, year, month, day);
        datePickerDialog.show();
    }

    private void submitPaymentDetails() {
        String voucherNumber = vouEditText.getText().toString().trim();
        String invoiceNumber = invoEditText.getText().toString().trim();
        String purchaseDate = purcEditText.getText().toString().trim();
        String totalAmount = totalEditText.getText().toString().trim();

        if (voucherNumber.isEmpty() ||
                invoiceNumber.isEmpty() ||
                purchaseDate.isEmpty() ||
                totalAmount.isEmpty()) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String paymentStatus = "";
        int selectedStatusId = paymentStatusGroup.getCheckedRadioButtonId();
        if (selectedStatusId == paymentPending.getId()) {
            paymentStatus = "Payment Pending";
        } else if (selectedStatusId == paymentDone.getId()) {
            paymentStatus = "Payment Done";
        } else {
            Toast.makeText(this, "Please select a payment status", Toast.LENGTH_SHORT).show();
            return;
        }

        dbHelper.addPurchaseReport(voucherNumber, invoiceNumber, purchaseDate, totalAmount, paymentStatus);
        Toast.makeText(this, "Report Added", Toast.LENGTH_SHORT).show();

        // Clear input fields
        vouEditText.setText("");
        invoEditText.setText("");
        purcEditText.setText("");
        totalEditText.setText("");
        paymentStatusGroup.clearCheck();

        // Reload the list of reports
        loadReports();
    }

    private void loadReports() {
        reportList = dbHelper.getAllReports();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reportList);
        reportListView.setAdapter(adapter);
    }

    private void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedReport = reportList.get(position);
        String voucher = selectedReport.split("\n")[0].split(":")[1].trim();
        dbHelper.deleteReportByVoucher(voucher);
        Toast.makeText(PurchaseReportActivity.this, "Report Deleted", Toast.LENGTH_SHORT).show();
        loadReports(); // Reload the list after deletion
    }
}
