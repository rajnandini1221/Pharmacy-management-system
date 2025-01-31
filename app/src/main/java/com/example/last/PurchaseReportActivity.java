package com.example.last;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.androidbrowserhelper.playbilling.provider.PaymentActivity;

public class PurchaseReportActivity extends AppCompatActivity {

    private EditText vouEditText, invoEditText, purcEditText, totalEditText;
    private RadioGroup paymentStatusGroup;
    private RadioButton paymentPending, paymentDone;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchasereportactivity); // Make sure to replace with your layout file name

        // Initialize the views
        vouEditText = findViewById(R.id.vouc);
        invoEditText = findViewById(R.id.innno);
        purcEditText = findViewById(R.id.purchd);
        totalEditText = findViewById(R.id.totlamt);

        paymentStatusGroup = findViewById(R.id.paymentStatusGroup);
        paymentPending = findViewById(R.id.paymentPending);
        paymentDone = findViewById(R.id.paymentDone);

        submitButton = findViewById(R.id.done);

        // Set a listener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPaymentDetails();
            }
        });
    }

    private void submitPaymentDetails() {
        // Get the input data from the user
        String voucherNumber = vouEditText.getText().toString().trim();
        String invoiceNumber = invoEditText.getText().toString().trim();
        String purchaseDate = purcEditText.getText().toString().trim();
        String totalAmount = totalEditText.getText().toString().trim();

        // Check if the required fields are filled
        if (voucherNumber.isEmpty() || invoiceNumber.isEmpty() || purchaseDate.isEmpty() || totalAmount.isEmpty()) {
            Toast.makeText(PaymentActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get the selected payment status
        String paymentStatus = "";
        int selectedStatusId = paymentStatusGroup.getCheckedRadioButtonId();
        if (selectedStatusId == paymentPending.getId()) {
            paymentStatus = "Payment Pending";
        } else if (selectedStatusId == paymentDone.getId()) {
            paymentStatus = "Payment Done";
        } else {
            Toast.makeText(PaymentActivity.this, "Please select a payment status", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show the entered details as a toast (or you can send this data to your server)
        String message = "Voucher: " + voucherNumber + "\n" +
                "Invoice: " + invoiceNumber + "\n" +
                "Purchase Date: " + purchaseDate + "\n" +
                "Total Amount: " + totalAmount + "\n" +
                "Payment Status: " + paymentStatus;

        Toast.makeText(PaymentActivity.this, message, Toast.LENGTH_LONG).show();

        // You can now process the data as needed, like saving it to a database or sending it to a server
    }
}

