package com.kadequart.android.quartrack.utils;

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
}