package com.example.last;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MedicineActivity extends AppCompatActivity {

    private EditText serialNoEditText, medicineNameEditText, packingEditText, genericNameEditText, supplierEditText;
    private Button submitButton;
    private TextView medicineListTextView;
    private MedicineDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicineactivity);

        dbHelper = new MedicineDatabaseHelper(this);

        serialNoEditText = findViewById(R.id.customer_name);
        medicineNameEditText = findViewById(R.id.medinam);
        packingEditText = findViewById(R.id.pack);
        genericNameEditText = findViewById(R.id.generic);
        supplierEditText = findViewById(R.id.medsupp);
        submitButton = findViewById(R.id.done);
        medicineListTextView = findViewById(R.id.medicine_list);

        // Load existing medicine records
        loadMedicineData();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serialNo = serialNoEditText.getText().toString().trim();
                String medicineName = medicineNameEditText.getText().toString().trim();
                String packing = packingEditText.getText().toString().trim();
                String genericName = genericNameEditText.getText().toString().trim();
                String supplier = supplierEditText.getText().toString().trim();

                if (serialNo.isEmpty() || medicineName.isEmpty() || packing.isEmpty() || genericName.isEmpty() || supplier.isEmpty()) {
                    Toast.makeText(MedicineActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.addMedicine(serialNo, medicineName, packing, genericName, supplier);
                    Toast.makeText(MedicineActivity.this, "Medicine Added Successfully!", Toast.LENGTH_SHORT).show();
                    loadMedicineData(); // Refresh the medicine list
                }
            }
        });
    }

    private void loadMedicineData() {
        List<String> medicineList = dbHelper.getAllMedicines();

        if (medicineList.isEmpty()) {
            medicineListTextView.setText("No records found");
        } else {
            StringBuilder builder = new StringBuilder();
            for (String record : medicineList) {
                builder.append(record).append("\n\n");
            }
            medicineListTextView.setText(builder.toString());
        }
    }
}
