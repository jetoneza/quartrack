package com.kadequart.android.quartrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by jeetkunedo on 19/05/2017.
 */

public class TransactionAdapter extends ArrayAdapter<Transaction> {
    public TransactionAdapter (Context context, ArrayList<Transaction> transactions) {
        super(context, 0, transactions);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
       View rowView = convertView;

        if (rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.transactions_list_item, parent, false);
        }

        Transaction transaction = getItem(position);

        TextView numberTextView = (TextView) rowView.findViewById(R.id.transaction_number);
        numberTextView.setText(transaction.getId());

        TextView amountTextView = (TextView) rowView.findViewById(R.id.transaction_amount);
        amountTextView.setText(transaction.getAmount() + "");

        return rowView;
    }
}
