package com.kadequart.android.quartrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class TransactionFormActivity extends AppCompatActivity {

    Spinner mTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        setContentView(R.layout.activity_transaction_form);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add New Transaction");

        mTypeSpinner = (Spinner) findViewById(R.id.type_spinner);

        ArrayList<String> types = new ArrayList<>();

        types.add("Salary");
        types.add("Payable");
        types.add("Spend");
        types.add("Donation");
        types.add("Offering");

        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types);
        mTypeSpinner.setAdapter(spinnerAdapter);
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
