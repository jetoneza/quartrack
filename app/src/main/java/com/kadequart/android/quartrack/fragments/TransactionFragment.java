package com.kadequart.android.quartrack.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kadequart.android.quartrack.R;
import com.kadequart.android.quartrack.Transaction;
import com.kadequart.android.quartrack.TransactionAdapter;
import com.kadequart.android.quartrack.TransactionFormActivity;
import com.kadequart.android.quartrack.TransactionsActivity;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class TransactionFragment extends Fragment {

  private RecyclerView.Adapter adapter;
  private RecyclerView recyclerView;

  private Realm realm;

  private RealmList<Transaction> transactions = new RealmList<>();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_transaction, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    realm = Realm.getDefaultInstance();

    View view = getView();

    FloatingActionButton addButton = (FloatingActionButton) view.findViewById(R.id.add_button);

    addButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getActivity(), TransactionFormActivity.class);
        startActivity(intent);
      }
    });

    TextView seeAllTextView = (TextView) view.findViewById(R.id.text_view_all);

    seeAllTextView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getActivity(), TransactionsActivity.class);
        startActivity(intent);
      }
    });

    recyclerView = (RecyclerView) view.findViewById(R.id.transactions_recycler_view);
    recyclerView.setHasFixedSize(true);

    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

    initializeAdapter();
    loadTransactions();
  }

  @Override
  public void onResume() {
    super.onResume();

    // TODO: Use better implementation.
    // Load transactions only after creation, deletion, or update success
    if (adapter == null) {
      initializeAdapter();
    }

    loadTransactions();
  }

  private void initializeAdapter () {
    adapter = new TransactionAdapter(transactions);

    recyclerView.setAdapter(adapter);
  }

  private void loadTransactions() {
    transactions.clear();
    List<Transaction> allTransactions = realm.where(Transaction.class).findAll();

    int limit = 5;

    // Only display 5
    if (allTransactions.size() > limit) {
      transactions.addAll(allTransactions.subList(0, limit));
    } else {
      transactions.addAll(allTransactions);
    }

    adapter.notifyDataSetChanged();
  }
}
