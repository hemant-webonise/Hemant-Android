package com.example.webonise.comweboniselabhemantandroidsharedpreferences;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SharedPreferenceActivity extends Activity {
    TextView SharedPref_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        Bundle bundle = getIntent().getExtras();
        String mes = bundle.getString("mes");
        init();
        SharedPref_input.setText(mes);
    }

    private void init() {
        SharedPref_input=(TextView)findViewById(R.id.SharedPref_input);
    }



}
