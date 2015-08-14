package com.example.webonise.comweboniselabhemantandroidsqliteassignment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;


public class DetailsFromSQLiteActivity extends Activity {
    TextView tv_d;
    DataBaseHandler handles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_from_sqlite);
        init();
//        String recieved = getIntent().getStringExtra("m");
//        tv_d.setText(recieved);
    }

    private void init() {
        tv_d = (TextView) findViewById(R.id.tv_d);
        handles = new DataBaseHandler(this);
        getDataFromSQLite();
    }

    private String getDataFromSQLite() {

        // Reading all Details
        Log.d("Reading: ", getString(com.example.webonise.comweboniselabhemantandroidsqliteassignment.R.string.reading_data));
        List<Details> details = handles.getAllDetails();

        for (Details det : details) {
            String log = String.format(getString(R.string.passing_string_infromSQLite), det.getDetailsId(), det.getDetailsName(), det.getDetailsAge(), det.getDetailsHt(), det.getDetailsWt());
            // Writing Details to logContacts
            Log.d("Name: ", log);

            tv_d.append(log);
        }
        return getString(R.string.proccesComplete);
    }
}
