package com.kadequart.android.quartrack;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

public class TransactionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Transactions");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.transactions_list_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Transaction> transactions = new ArrayList<>();

//        transactions.add(new Transaction("#00000325", 9842.99));
//        transactions.add(new Transaction("#00000325", 9842.99));
//        transactions.add(new Transaction("#00000324", 9209.24));
//        transactions.add(new Transaction("#00000323", 9389.44));
//        transactions.add(new Transaction("#00000322", 5523.23));
//        transactions.add(new Transaction("#00000321", 8171.44));
//        transactions.add(new Transaction("#00000324", 9209.24));
//        transactions.add(new Transaction("#00000323", 9389.44));
//        transactions.add(new Transaction("#00000322", 5523.23));
//        transactions.add(new Transaction("#00000321", 8171.44));
//        transactions.add(new Transaction("#00000324", 9209.24));
//        transactions.add(new Transaction("#00000323", 9389.44));
//        transactions.add(new Transaction("#00000322", 5523.23));
//        transactions.add(new Transaction("#00000321", 8171.44));
//        transactions.add(new Transaction("#00000324", 9209.24));
//        transactions.add(new Transaction("#00000323", 9389.44));
//        transactions.add(new Transaction("#00000322", 5523.23));
//        transactions.add(new Transaction("#00000321", 8171.44));

        RecyclerView.Adapter adapter = new TransactionAdapter(transactions);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);
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
