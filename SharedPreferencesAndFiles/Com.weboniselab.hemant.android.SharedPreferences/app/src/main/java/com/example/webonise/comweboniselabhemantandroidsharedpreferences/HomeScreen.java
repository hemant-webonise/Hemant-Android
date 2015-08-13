package com.example.webonise.comweboniselabhemantandroidsharedpreferences;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class HomeScreen extends Activity implements View.OnClickListener {
    Button btn_activate, btn_SP;
    Button btn_FileOpen;
    EditText et_receive, et_name, et_ht, et_wt;
    EditText et_send;
    EditText et_receiveFromFile;
    final String FILENAME = "new.txt";
    final String PREFER = "prefer";
    SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Toast.makeText(HomeScreen.this, "Old value in Shared-Preferences" + pref.getString("Passed_string", "Def"), Toast.LENGTH_LONG).show();
    }

    private void init() {
        et_receive = (EditText) findViewById(R.id.et_receive);
        et_send = (EditText) findViewById(R.id.et_send);
        et_receiveFromFile = (EditText) findViewById(R.id.et_receiveFromFile);
        et_name = (EditText) findViewById(R.id.et_name);
        et_ht = (EditText) findViewById(R.id.et_ht);
        et_wt = (EditText) findViewById(R.id.et_wt);
        btn_activate = (Button) findViewById(R.id.btn_activate);
        btn_FileOpen = (Button) findViewById(R.id.btn_FileOpen);
        btn_SP = (Button) findViewById(R.id.btn_SP);
        btn_SP.setOnClickListener(this);
        btn_FileOpen.setOnClickListener(this);
        btn_activate.setOnClickListener(this);
        pref = getApplicationContext().getSharedPreferences(PREFER, MODE_PRIVATE);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_activate:

                dataTransferUsingSharedPreferences();
                dataTransferUsingFile();
                break;

            case R.id.btn_FileOpen:
                Toast.makeText(HomeScreen.this, readFromFile(), Toast.LENGTH_LONG).show();
                Intent FileDisplay = new Intent(HomeScreen.this, FileOpennerActivity.class);
                FileDisplay.putExtra("message", readFromFile());
                startActivity(FileDisplay);
                break;

            case R.id.btn_SP:
                if (detailFilledCheck()) {
                    Intent SPDisplay = new Intent(HomeScreen.this, SharedPreferenceActivity.class);
                    SPDisplay.putExtra("mes", dataTransferUsingSharedPreferences());
                    startActivity(SPDisplay);

                }


                break;
        }


    }


    private boolean detailFilledCheck() {

        if (TextUtils.isEmpty(et_name.getText().toString())) {
            et_name.setError("Please fill Name");


        } else if (TextUtils.isEmpty(et_ht.getText().toString())) {
            et_ht.setError("Please fill Height");

        } else if (TextUtils.isEmpty(et_wt.getText().toString())) {
            et_wt.setError("Please fill Weight");
            return false;

        }
        return true;


    }


    private void dataTransferUsingFile() {
        writeToFile(et_send.getText().toString());
        et_receiveFromFile.append(readFromFile());
    }

    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(FILENAME, Context.MODE_PRIVATE));
            Toast.makeText(HomeScreen.this, "File created", Toast.LENGTH_LONG).show();
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile() {
        String ret = "";
        try {
//            /data/data/com.example.webonise.comweboniselabhemantandroidsharedpreferences/files/new.txt:
            InputStream inputStream = openFileInput(FILENAME);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("prefer", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", getString(R.string.FNF) + e.toString());
        } catch (IOException e) {
            Log.e("login activity", getString(R.string.CANTOPEN) + e.toString());
        }
        return ret;
    }


    private String dataTransferUsingSharedPreferences() {

        SharedPreferences.Editor editor = pref.edit();

        editor.putInt("Counter", 1);
        editor.putString("name", et_name.getText().toString());
        editor.putInt("weight", Integer.parseInt(et_wt.getText().toString()));
        editor.putInt("height", Integer.parseInt(et_ht.getText().toString()));
        editor.putString("Passed_string", et_send.getText().toString());

        // java.lang.NumberFormatException: Invalid int: ""

        editor.commit();


        String passing_string = pref.getString("Passed_string", null);

        et_receive.append(passing_string);

        return " Name : " + pref.getString("name", null) + " Height : " + pref.getInt("height", 0) + " Weight : " + pref.getInt("weight", 0);

    }
}
