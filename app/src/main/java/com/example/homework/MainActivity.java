package com.example.homework;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener, BlankFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment hello = new FirstFragment();
        FragmentTransaction hi = getSupportFragmentManager().beginTransaction();
        hi.add(R.id.hello, hello, "My_FragmentName");
        hi.commit();
    }

    @Override
    public void onFragmentInteraction(String a, int position) {

        Fragment newFragment = BlankFragment.newInstance(a, position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
        transaction.replace(R.id.hello, newFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();

    }

    @Override
    public void onFragmentInteractions() {
        Fragment newFragment = new FirstFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
        transaction.replace(R.id.hello, newFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }
}
