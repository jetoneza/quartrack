package com.kadequart.android.quartrack;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kadequart.android.quartrack.fragments.TransactionFragment;

public class MainActivity extends AppCompatActivity {

  private TransactionFragment transactionFragment = new TransactionFragment();

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    initializeFragments();
  }

  private void initializeFragments () {
    getFragmentManager().beginTransaction().add(R.id.transaction_fragment, transactionFragment).commit();
  }

}
