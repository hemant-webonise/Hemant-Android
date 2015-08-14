package com.example.webonise.comweboniselabhemantandroidsqliteassignment;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class DetailsFromSQLite extends Activity {
    TextView tv_d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_from_sqlite);
        init();
        String recieved = getIntent().getStringExtra("m");
        tv_d.setText(recieved);
    }

    private void init() {
        tv_d = (TextView) findViewById(R.id.tv_d);
    }
}
