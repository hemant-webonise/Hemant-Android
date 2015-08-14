package com.example.webonise.comweboniselabhemantandroidsqliteassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class HomeScreenActivity extends Activity implements View.OnClickListener {
    EditText etName, etAge, etHeight, et_weight;
    Button btnSave, btnSendToDB;
    DataBaseHandler handles;
    TextView tvDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        init();
    }

    private void init() {
        etName = (EditText) findViewById(R.id.et_name);
        etAge = (EditText) findViewById(R.id.et_age);
        etHeight = (EditText) findViewById(R.id.et_height);
        et_weight = (EditText) findViewById(R.id.et_weight);
        btnSave = (Button) findViewById(R.id.btn_Save);
        btnSendToDB = (Button) findViewById(R.id.btn_SendToDB);
        tvDb = (TextView) findViewById(R.id.tv_db);
        handles = new DataBaseHandler(this);
        btnSave.setOnClickListener(this);
        btnSendToDB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_Save:
                if (isDetailFilled()) {
                    setStoredDetails();
                    Intent Display = new Intent(HomeScreenActivity.this, DetailsFromSQLiteActivity.class);
                    Display.putExtra(getString(R.string.bridgePassed), getStoredDetails());
                    startActivity(Display);
                }
                break;
            case R.id.btn_SendToDB:
                Intent Display = new Intent(HomeScreenActivity.this, DetailsFromSQLiteActivity.class);
                Display.putExtra(getString(R.string.bridgePassed), getStoredDetails());
                startActivity(Display);
                break;
        }

    }

    private void setStoredDetails() {
        Details d = new Details();
        d.setDetailsAge(Integer.parseInt(etAge.getText().toString()));
        d.setDetailsWt(Integer.parseInt(et_weight.getText().toString()));
        d.setDetailsName(etName.getText().toString());
        d.setDetailsHt(Integer.parseInt(etHeight.getText().toString()));
        handles.addDetails(d);
    }


    private String getStoredDetails() {

        // Reading all Details
        Log.d(getString(R.string.logKeyReading), getString(R.string.toast_reading));
        List<Details> details = handles.getAllDetails();

        for (Details det : details) {
            String log = String.format(getString(R.string.da_string), det.getDetailsId(), det.getDetailsName(), det.getDetailsAge(), det.getDetailsHt(), det.getDetailsWt());
            // Writing Details to logContacts
            Log.d(getString(R.string.logKeyname), log);

            tvDb.append(log);
        }
        return getString(R.string.doneParsing);
    }

    private boolean isDetailFilled() {

        if (TextUtils.isEmpty(etName.getText().toString())) {
            etName.setError(getString(R.string.et_name_err));
        } else if (TextUtils.isEmpty(etAge.getText().toString())) {
            etAge.setError(getString(R.string.et_age_err));
        } else if (TextUtils.isEmpty(etHeight.getText().toString())) {
            etHeight.setError(getString(R.string.et_ht_err));

        } else if (TextUtils.isEmpty(et_weight.getText().toString())) {
            et_weight.setError(getString(R.string.et_wt_err));
        } else {
            return true;
        }
        return false;
    }
}
