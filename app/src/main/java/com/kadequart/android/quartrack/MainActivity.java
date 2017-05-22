package com.kadequart.android.quartrack;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    private Realm realm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        realm = Realm.getDefaultInstance();

        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TransactionFormActivity.class);
                startActivity(intent);
            }
        });

        TextView seeAllTextView = (TextView) findViewById(R.id.text_view_all);

        seeAllTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TransactionsActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.transactions_recycler_view);
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
        }

        adapter.notifyDataSetChanged();
    }

    private List<Transaction> loadTransactions() {
      RealmResults<Transaction> transactions = realm.where(Transaction.class).findAll();

      int limit = 5;

      if (transactions.size() > limit) {
        List<Transaction> subList = transactions.subList(0, limit);

        return subList;
      }

      return transactions;
    }
}
