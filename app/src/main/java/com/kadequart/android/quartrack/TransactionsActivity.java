package com.kadequart.android.quartrack;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class TransactionsActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Transactions");

        realm = Realm.getDefaultInstance();

        recyclerView = (RecyclerView) findViewById(R.id.transactions_list_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adapter == null) {
            List<Transaction> transactions = loadTransactions();

            adapter = new TransactionAdapter(transactions);

            recyclerView.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }
    }

    private List<Transaction> loadTransactions() {
        RealmResults<Transaction> transactions = realm.where(Transaction.class).findAll();

        return transactions;
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
