package com.kadequart.android.quartrack;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class MainActivity extends AppCompatActivity {

  private RecyclerView.Adapter adapter;
  private RecyclerView recyclerView;

  private Realm realm;

  private RealmList<Transaction> _transactions = new RealmList<>();

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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        initializeAdapter();
        loadTransactions();
    }

  @Override
  protected void onResume() {
    super.onResume();

    // TODO: Use better implementation.
    // Load transactions only after creation, deletion, or update success
    if (adapter == null) {
      initializeAdapter();
    }

    loadTransactions();
  }

  private void initializeAdapter () {
    adapter = new TransactionAdapter(_transactions);

    recyclerView.setAdapter(adapter);
  }

  private void loadTransactions() {
    _transactions.clear();
    List<Transaction> allTransactions = realm.where(Transaction.class).findAll();

    int limit = 5;

    // Only display 5
    if (allTransactions.size() > limit) {
      _transactions.addAll(allTransactions.subList(0, limit));
    } else {
      _transactions.addAll(allTransactions);
    }

    adapter.notifyDataSetChanged();
  }
}
