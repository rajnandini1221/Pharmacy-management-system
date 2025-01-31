package com.example.last;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SaleReportActivity extends AppCompatActivity {
    private EditText vouc, innno, saled, totlamt;
    private RadioGroup paymentStatusGroup;
    private RadioButton paymentPending, paymentDone;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salereportactivity);

        // Initialize UI components
        vouc = findViewById(R.id.vouc);
        innno = findViewById(R.id.innno);
        saled = findViewById(R.id.saled);
        totlamt = findViewById(R.id.totlamt);
        paymentStatusGroup = findViewById(R.id.paymentStatusGroup);
        paymentPending = findViewById(R.id.paymentPending);
        paymentDone = findViewById(R.id.paymentDone);
        submitButton = findViewById(R.id.done);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmit();
            }
        });
    }

    private void handleSubmit() {
        String voucherNumber = vouc.getText().toString().trim();
        String invoiceNumber = innno.getText().toString().trim();
        String saleDate = saled.getText().toString().trim();
        String totalAmount = totlamt.getText().toString().trim();
        String paymentStatus = paymentPending.isChecked() ? "Pending" : "Done";

        if (voucherNumber.isEmpty() || invoiceNumber.isEmpty() || saleDate.isEmpty() || totalAmount.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Here, you can send the data to a database or API
        Toast.makeText(this, "Voucher submitted successfully!", Toast.LENGTH_SHORT).show();
    }
}
