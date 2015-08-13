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
import android.widget.Toast;

import java.util.List;


public class HomeScreenActivity extends Activity implements View.OnClickListener {
    EditText et_name, et_age, et_ht, et_wt;
    Button btn_Save, btn_SendToDB;
    DataBaseHandler handles;
    TextView tv_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        init();
    }

    private void init() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_age = (EditText) findViewById(R.id.et_age);
        et_ht = (EditText) findViewById(R.id.et_height);
        et_wt = (EditText) findViewById(R.id.et_weight);
        btn_Save = (Button) findViewById(R.id.btn_Save);
        btn_SendToDB = (Button) findViewById(R.id.btn_SendToDB);
        tv_db = (TextView) findViewById(R.id.tv_db);
        handles = new DataBaseHandler(this);
        btn_Save.setOnClickListener(this);
        btn_SendToDB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_Save:
                if (isDetailFilled()) {
                    setDataToSQLite();
                    Toast.makeText(this, getString(R.string.toast_string_dbstrated), Toast.LENGTH_LONG).show();
                    Intent Display = new Intent(HomeScreenActivity.this, DetailsFromSQLiteActivity.class);
                    Display.putExtra("m", getDataFromSQLite());
                    startActivity(Display);
                    Toast.makeText(this, "....", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_SendToDB:
                Intent Display = new Intent(HomeScreenActivity.this, DetailsFromSQLiteActivity.class);
                Display.putExtra("m", getDataFromSQLite());
                startActivity(Display);
                break;
        }

    }

    private void setDataToSQLite() {
        Details d = new Details();
        d.setDetails_age(Integer.parseInt(et_age.getText().toString()));
        d.setDetails_wt(Integer.parseInt(et_wt.getText().toString()));
        d.setDetails_name(et_name.getText().toString());
        d.setDetails_ht(Integer.parseInt(et_ht.getText().toString()));
        handles.addDetails(d);
    }


    private String getDataFromSQLite() {

        // Reading all Details
        Log.d("Reading: ", getString(R.string.toast_reading));
        List<Details> details = handles.getAllDetails();

        for (Details det : details) {
            String log = String.format(getString(R.string.da_string), det.getDetails_id(), det.getDetails_name(), det.getDetails_age(), det.getDetails_ht(), det.getDetails_wt());
            // Writing Details to logContacts
            Log.d("Name: ", log);

            tv_db.append(log);
        }
        return "Done parsing";
    }

    private boolean isDetailFilled() {

        if (TextUtils.isEmpty(et_name.getText().toString())) {
            et_name.setError(getString(R.string.et_name_err));
        } else if (TextUtils.isEmpty(et_age.getText().toString())) {
            et_age.setError(getString(R.string.et_age_err));
        } else if (TextUtils.isEmpty(et_ht.getText().toString())) {
            et_ht.setError(getString(R.string.et_ht_err));

        } else if (TextUtils.isEmpty(et_wt.getText().toString())) {
            et_wt.setError(getString(R.string.et_wt_err));
        } else {
            return true;
        }
        return false;
    }
}
