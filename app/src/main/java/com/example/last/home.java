package com.example.last;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class home extends AppCompatActivity {


    private CardView totalCustomers, medicine, supplier, purchaseReport, saleReport,  outOfStock, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Initializing CardViews
        totalCustomers = findViewById(R.id.totalcus);
        medicine = findViewById(R.id.medi);
        supplier = findViewById(R.id.supp);
        purchaseReport = findViewById(R.id.purr);
        saleReport = findViewById(R.id.sale);
        outOfStock = findViewById(R.id.out);
        logout = findViewById(R.id.log);

        // Setting click listeners
        totalCustomers.setOnClickListener(view -> openActivity(TotalCustomersActivity.class));
        medicine.setOnClickListener(view -> openActivity(MedicineActivity.class));
        supplier.setOnClickListener(view -> openActivity(SupplierActivity.class));
        purchaseReport.setOnClickListener(view -> openActivity(PurchaseReportActivity.class));
        saleReport.setOnClickListener(view -> openActivity(SaleReportActivity.class));
        outOfStock.setOnClickListener(view -> openActivity(OutOfStockActivity.class));
        logout.setOnClickListener(view -> logoutUser());
    }

    // Method to navigate to another activity
    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(home.this, activityClass);
        startActivity(intent);
    }

    // Logout method
    private void logoutUser() {
        Intent intent = new Intent(home.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
