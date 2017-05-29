package com.kadequart.android.quartrack;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jeetkunedo on 19/05/2017.
 */

public class Transaction extends RealmObject {
    private int id;
    private double amount;

    public int getId () { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount () {
        return amount;
    }

    public void setAmount (double amount) {
        this.amount = amount;
    }

    static int generateNextId (Realm realm) {
        Number size = realm.where(Transaction.class).max("id");

        if (size != null) {
            return size.intValue() + 1;
        }

        return 1;
    }

}
