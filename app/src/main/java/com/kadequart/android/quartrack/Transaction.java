package com.kadequart.android.quartrack;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jeetkunedo on 19/05/2017.
 */

public class Transaction extends RealmObject {
    private String id;
    private double amount;

    public String getId () { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount () {
        return amount;
    }

    public void setAmount (double amount) {
        this.amount = amount;
    }
}
