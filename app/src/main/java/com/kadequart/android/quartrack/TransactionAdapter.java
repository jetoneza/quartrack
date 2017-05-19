package com.kadequart.android.quartrack;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jeetkunedo on 19/05/2017.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private ArrayList<Transaction> data;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    public TransactionAdapter(ArrayList<Transaction> dataSet) {
        data = dataSet;
    }

    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transactions_list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View rowView = holder.view;

        Transaction transaction = data.get(position);

        TextView numberTextView = (TextView) rowView.findViewById(R.id.transaction_number);
        numberTextView.setText(transaction.getId());

        TextView amountTextView = (TextView) rowView.findViewById(R.id.transaction_amount);
        amountTextView.setText(transaction.getAmount() + "");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
