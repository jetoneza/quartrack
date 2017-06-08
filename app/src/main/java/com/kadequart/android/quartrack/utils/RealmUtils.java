package com.kadequart.android.quartrack.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

  public static String formatDate (Date date) {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    return formatter.format(date);
  }
}