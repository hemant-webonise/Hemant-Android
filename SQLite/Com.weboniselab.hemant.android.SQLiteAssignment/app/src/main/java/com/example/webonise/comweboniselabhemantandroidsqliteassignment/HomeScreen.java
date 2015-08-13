package com.example.webonise.comweboniselabhemantandroidsqliteassignment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


public class HomeScreen extends Activity implements View.OnClickListener {
    EditText et_name, et_age, et_ht, et_wt;
    Button btn_Save,btn_SendToDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        init();
    }

    private void init() {
        et_name=(EditText)findViewById(R.id.et_name);
        et_age=(EditText)findViewById(R.id.et_age);
        et_ht=(EditText)findViewById(R.id.et_height);
        et_wt=(EditText)findViewById(R.id.et_weight);
        btn_Save = (Button)findViewById(R.id.btn_Save);
        btn_SendToDB = (Button)findViewById(R.id.btn_SendToDB);
        btn_Save.setOnClickListener(this);
        btn_SendToDB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_Save:
                if (detailFilledCheck()) {
                    Toast.makeText(this,"Data Saving started....",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_SendToDB:

                    Intent Display = new Intent(HomeScreen.this, DetailsFromSQLite.class);
                    Display.putExtra("m", dataFromSQLite());
                    startActivity(Display);
                    Toast.makeText(this,"....",Toast.LENGTH_LONG).show();
                break;

        }

    }

    private String dataFromSQLite() {
        //
        return "From SQLite";
    }

    private boolean detailFilledCheck() {

        if (TextUtils.isEmpty(et_name.getText().toString())) {
            et_name.setError("Please fill Name");
        }
        else if (TextUtils.isEmpty(et_age.getText().toString())) {
            et_age.setError("Please fill Age");
        }

        else if (TextUtils.isEmpty(et_ht.getText().toString())) {
            et_ht.setError("Please fill Height");

        }
        else if (TextUtils.isEmpty(et_wt.getText().toString())) {
            et_wt.setError("Please fill Weight");
        }
        else {
            return true;
        }
        return false;
    }
}
