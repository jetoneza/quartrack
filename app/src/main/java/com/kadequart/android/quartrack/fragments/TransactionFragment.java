package com.kadequart.android.quartrack.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import io.realm.Sort;

public class TransactionFragment extends Fragment {

  private RecyclerView.Adapter adapter;
  private RecyclerView recyclerView;

  private Realm realm;

  private RealmList<Transaction> transactions = new RealmList<>();

  private OnTransactionSelectedListener selectedListener;

  private View.OnClickListener onClickListener = new View.OnClickListener() {

    @Override
    public void onClick(View view) {
      if (selectedListener != null) {
        RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(view);

        Transaction transaction = transactions.get(holder.getAdapterPosition());

        selectedListener.onTransactionSelected(transaction);
      }
    }
  };

  private RecyclerView.OnChildAttachStateChangeListener attachListener = new RecyclerView.OnChildAttachStateChangeListener() {

    @Override
    public void onChildViewAttachedToWindow(View view) {
      if (selectedListener != null) {
        view.setOnClickListener(onClickListener);
      }
    }

    @Override
    public void onChildViewDetachedFromWindow(View view) {

    }
  };

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    try {
      selectedListener = (OnTransactionSelectedListener) activity;
    } catch (ClassCastException e) {
      throw new ClassCastException(activity.toString() + " must implement OnTransactionSelectedListener");
    }
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    realm = Realm.getDefaultInstance();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_transaction, container, false);

    setupViews(view);

    initializeAdapter();
    loadTransactions();

    return view;
  }

  public void setupViews(View view) {
    Activity parentActivity = getActivity();

    FloatingActionButton addButton = (FloatingActionButton) view.findViewById(R.id.add_button);
    TextView seeAllTextView = (TextView) view.findViewById(R.id.text_view_all);

    addButton.setOnClickListener(new ClickListener(parentActivity));
    seeAllTextView.setOnClickListener(new ClickListener(parentActivity));

    recyclerView = (RecyclerView) view.findViewById(R.id.transactions_recycler_view);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    recyclerView.addOnChildAttachStateChangeListener(attachListener);
  }

  private class ClickListener implements View.OnClickListener {
    Activity parentActivity;

    ClickListener(Activity parentActivity) {
      this.parentActivity = parentActivity;
    }

    @Override
    public void onClick(View view) {
      Intent intent = null;

      switch (view.getId()) {
        case R.id.add_button:
          intent = new Intent(parentActivity, TransactionFormActivity.class);
          break;

        case R.id.text_view_all:
          intent = new Intent(parentActivity, TransactionsActivity.class);
          break;
      }

      startActivity(intent);
    }
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
    List<Transaction> allTransactions = realm.where(Transaction.class).findAllSorted("id", Sort.DESCENDING);

    int limit = 5;

    // Only display 5
    if (allTransactions.size() > limit) {
      transactions.addAll(allTransactions.subList(0, limit));
    } else {
      transactions.addAll(allTransactions);
    }

    adapter.notifyDataSetChanged();
  }

  public interface OnTransactionSelectedListener {
    public void onTransactionSelected(Transaction transaction);
  }
}
