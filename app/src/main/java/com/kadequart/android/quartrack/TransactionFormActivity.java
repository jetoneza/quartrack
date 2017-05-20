package com.kadequart.android.quartrack;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;

public class TransactionFormActivity extends AppCompatActivity {

    private Realm realm;

    private Spinner typeSpinner;
    private EditText amountEditText;
    private EditText notesEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_form);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add New Transaction");

        initializeViews();
        initializeListeners();
    }

    public void initializeViews () {
        typeSpinner = (Spinner) findViewById(R.id.type_spinner);
        amountEditText = (EditText) findViewById(R.id.amount_input);
        notesEditText = (EditText) findViewById(R.id.notes_input);
        submitButton = (Button) findViewById(R.id.submit_button);

        initializeSpinner();
    }

    public void initializeSpinner () {
        ArrayList<String> types = new ArrayList<>();

        types.add("Salary");
        types.add("Payable");
        types.add("Spend");
        types.add("Donation");
        types.add("Offering");

        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types);
        typeSpinner.setAdapter(spinnerAdapter);
    }

    public void initializeListeners() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTransaction();
            }
        });
    }

    public void addTransaction () {
        // TODO: get spinner value
        String amountText = amountEditText.getText().toString();
        String notes = notesEditText.getText().toString();

        double amount = Double.parseDouble(amountText);

        realm.beginTransaction();

        Transaction transaction = realm.createObject(Transaction.class);
        transaction.setId("#00000325");
        transaction.setAmount(amount);

        realm.commitTransaction();

        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(TransactionFormActivity.this, MainActivity.class);
        startActivity(intent);
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
