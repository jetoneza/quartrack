package com.kadequart.android.quartrack;

import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kadequart.android.quartrack.fragments.TransactionFragment;
import com.kadequart.android.quartrack.fragments.TransactionViewFragment;

public class MainActivity extends AppCompatActivity implements TransactionFragment.OnTransactionSelectedListener {

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

  @Override
  public void onTransactionSelected(Transaction transaction) {
    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.transaction_fragment, TransactionViewFragment.newInstance(transaction));
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }
}
