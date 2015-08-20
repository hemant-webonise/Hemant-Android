package com.example.webonise.FragmentAssignment;

import android.app.Activity;
;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeScreenFragment homeScreenFragment = new HomeScreenFragment();

        fragmentTransaction.add(R.id.fragment_container, homeScreenFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 1) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HomeScreenFragment homeScreenFragment = new HomeScreenFragment();
           /* Animation reverse logic is done in HomeScreenFragments logic.*/
            fragmentTransaction.replace(R.id.fragment_container, homeScreenFragment);
            fragmentTransaction.commit();
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
       super.onBackPressed();
    }
}
