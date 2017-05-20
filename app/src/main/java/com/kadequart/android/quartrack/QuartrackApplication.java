package com.kadequart.android.quartrack;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by leonapse on 5/20/2017.
 */
public class QuartrackApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();

    Realm.init(this);

    if (BuildConfig.DEBUG) {
      Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
          .deleteRealmIfMigrationNeeded()
          .build());

      return;
    }

    Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
        .build());
  }
}
