package com.kadequart.android.quartrack.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kadequart.android.quartrack.R;
import com.kadequart.android.quartrack.Transaction;
import com.kadequart.android.quartrack.utils.StringUtils;

public class TransactionViewFragment extends Fragment {

  Transaction transaction;

  public static TransactionViewFragment newInstance(Transaction transaction) {
    TransactionViewFragment fragment = new TransactionViewFragment();
    fragment.transaction = transaction;

    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_transaction_view, container, false);

    TextView numberTextView = (TextView) view.findViewById(R.id.text_view_transaction_number);
    numberTextView.setText(transaction.getId() + "");

    TextView amountTextView = (TextView) view.findViewById(R.id.text_view_amount);
    amountTextView.setText(transaction.getAmount() + "");

    TextView notesTextView = (TextView) view.findViewById(R.id.text_view_notes);
    notesTextView.setText(transaction.getNotes());

    TextView typeTextView = (TextView) view.findViewById(R.id.text_view_type);
    typeTextView.setText(StringUtils.capitalize(transaction.getType()));

    return view;
  }
}
