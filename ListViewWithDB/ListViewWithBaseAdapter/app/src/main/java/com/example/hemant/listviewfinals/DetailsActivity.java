package com.example.hemant.listviewfinals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class DetailsActivity extends Activity implements View.OnClickListener {
    EditText etName;
    EditText etAge;
    EditText etWeight;
    EditText etHeight;
    Button btnSave;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
    }

    public void initView() {
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etWeight = (EditText) findViewById(R.id.etWeight);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnSave:
                if (TextUtils.isEmpty(etName.getText().toString())) {
                    etName.setError(getString(R.string.et_name_err));
                } else if (TextUtils.isEmpty((etAge.getText().toString()))) {
                    etAge.setError(getString(R.string.et_age_err));
                } else if (TextUtils.isEmpty((etHeight.getText().toString()))) {
                    etHeight.setError(getString(R.string.et_ht_err));
                } else if (TextUtils.isEmpty((etWeight.getText().toString()))) {
                    etWeight.setError(getString(R.string.et_wt_err));
                } else {
                    DetailsDBAdapter personDatabaseHelper = new DetailsDBAdapter(this);
                    Details personDetails = new Details();
                    personDetails.setName(etName.getText().toString());
                    personDetails.setHeight(etHeight.getText().toString());
                    personDetails.setWeight(etWeight.getText().toString());
                    personDetails.setAge(etWeight.getText().toString());
                    personDatabaseHelper.createDetails(personDetails);
                    personDatabaseHelper.close();
                    Toast.makeText(getApplicationContext(), getString(R.string.successDatabase), Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.btnClear:
                  /*First Approach*/

//                etName.getText().clear();
//                etAge.getText().clear();
//                etHeight.getText().clear();
//                etWeight.getText().clear();

                /*Better Solution but read about savedStatus of activity */
                if (view == btnClear) {
                    startActivity(new Intent(DetailsActivity.this, DetailsActivity.class));
                }
                /*Creating a ViewGroup is another approach*/

        }
    }
}
