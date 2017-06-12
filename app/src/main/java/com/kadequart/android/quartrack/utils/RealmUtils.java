package com.kadequart.android.quartrack.utils;

import com.kadequart.android.quartrack.Transaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

public class RealmUtils {

  /**
   * Generate next id for realm object
   * @param realm
   * @param classToSearch
   * @return
   */
  public static int getNextId(Realm realm, Class classToSearch) {
    if (realm.where(classToSearch).max("id") != null) {
      return Integer.parseInt(realm.where(classToSearch).max("id").toString()) + 1;
    }

    return 1;
  }

  public static double getBalance() {
    List<Transaction> transactions = Realm.getDefaultInstance().where(Transaction.class).findAll();

    double balance = 0;

    for (Transaction transaction : transactions) {
      balance += transaction.getAmount();
    }

    return balance;
  }

  public static String formatDate (Date date) {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    return formatter.format(date);
  }
}