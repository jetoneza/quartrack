package com.kadequart.android.quartrack;

/**
 * Created by jeetkunedo on 19/05/2017.
 */

public class Transaction {
    private int id;
    private float amount;

    public Transaction (int mId, float mAmount) {
       id = mId;
       amount = mAmount;
    }

    public int getId () {
        return id;
    }

    public float getAmount () {
        return amount;
    }
}
