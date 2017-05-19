package com.kadequart.android.quartrack;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class TransactionFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_form);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add New Transaction");

        Spinner typeSpinner = (Spinner) findViewById(R.id.type_spinner);

        ArrayList<String> types = new ArrayList<>();

        types.add("Salary");
        types.add("Payable");
        types.add("Spend");
        types.add("Donation");
        types.add("Offering");

        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types);
        typeSpinner.setAdapter(spinnerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
