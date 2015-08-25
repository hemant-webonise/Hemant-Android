package com.example.webonise.listviewandsqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class DetailsActivity extends Activity implements View.OnClickListener {
    EditText etName, etAge, etHeight, etWeight;
    Button btnSave;
    DetailsDBAdapter dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
    }

    private void init() {
        etName = (EditText) findViewById(R.id.et_name);
        etAge = (EditText) findViewById(R.id.et_age);
        etHeight = (EditText) findViewById(R.id.et_height);
        etWeight = (EditText) findViewById(R.id.et_weight);
        btnSave = (Button) findViewById(R.id.btn_Save);
        btnSave.setOnClickListener(this);
        dbHelper = new DetailsDBAdapter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Save:
                if (isDetailFilled()) {
                    Intent backScreen = new Intent(DetailsActivity.this,ListViewAdaptorHomeScreenActivity.class);
                    dbHelper.open();
                    dbHelper.createDetails(etName.getText().toString(), String.format(getString(R.string.DetailsString), etAge.getText().toString()),etWeight.getText().toString(),etHeight.getText().toString());
                    dbHelper.close();
                    finish();
                }
                break;
        }
    }


    private boolean isDetailFilled() {

        if (TextUtils.isEmpty(etName.getText().toString())) {
            etName.setError(getString(R.string.et_name_err));
        } else if (TextUtils.isEmpty(etAge.getText().toString())) {
            etAge.setError(getString(R.string.et_age_err));
        } else if (TextUtils.isEmpty(etHeight.getText().toString())) {
            etHeight.setError(getString(R.string.et_ht_err));

        } else if (TextUtils.isEmpty(etWeight.getText().toString())) {
            etWeight.setError(getString(R.string.et_wt_err));
        } else {
            return true;
        }
        return false;
    }

}
