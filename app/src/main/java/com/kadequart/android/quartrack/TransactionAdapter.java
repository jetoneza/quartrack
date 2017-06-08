package com.kadequart.android.quartrack;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kadequart.android.quartrack.utils.RealmUtils;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by jeetkunedo on 19/05/2017.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private RealmList<Transaction> data;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    public TransactionAdapter(RealmList<Transaction> data) {
      this.data = data;
    }

    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transactions_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View rowView = holder.view;

        Transaction transaction = data.get(position);

        TextView numberTextView = (TextView) rowView.findViewById(R.id.transaction_number);
        numberTextView.setText(transaction.getId() + "");

        TextView amountTextView = (TextView) rowView.findViewById(R.id.transaction_amount);
        amountTextView.setText(transaction.getAmount() + "");

        TextView dateTextView = (TextView) rowView.findViewById(R.id.date_text_view);
        dateTextView.setText(RealmUtils.formatDate(transaction.getCreatedAt()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
