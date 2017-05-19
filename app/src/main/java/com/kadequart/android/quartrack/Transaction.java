package com.kadequart.android.quartrack;

/**
 * Created by jeetkunedo on 19/05/2017.
 */

public class Transaction {
    private String id;
    private double amount;

    public Transaction (String mId, double mAmount) {
       id = mId;
       amount = mAmount;
    }

    public String getId () {
        return id;
    }

    public double getAmount () {
        return amount;
    }
}
