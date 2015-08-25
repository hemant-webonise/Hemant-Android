package com.example.webonise.FragmentAssignment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeScreenFragment homeScreenFragment = new HomeScreenFragment();

        fragmentTransaction.add(R.id.fragment_container, homeScreenFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 1) {
            super.onBackPressed();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HomeScreenFragment homeScreenFragment = new HomeScreenFragment();
//          fragmentTransaction.setCustomAnimations( R.anim.slide_out_right,R.anim.slide_in_left);
            fragmentTransaction.add(R.id.fragment_container, homeScreenFragment);
            fragmentTransaction.commit();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
