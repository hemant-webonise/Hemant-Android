package com.example.webonise.comweboniselabhemantandroidsharedpreferences;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class FileOpennerActivity extends Activity {
    TextView file_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_openner);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");
        init();
        file_input.setText(message);
    }

    private void init() {
        file_input=(TextView)findViewById(R.id.file_input);
    }


}
