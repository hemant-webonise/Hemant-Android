package com.example.webonise.comweboniselabhemantandroidserviceandbroadcast;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {
    Button btn_Start, btn_Stop, btn_Bind, btn_Unbind, btn_add;
    EditText text_Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        btn_add.setOnClickListener(this);
    }




     void initialize() {
        btn_add = (Button) findViewById(R.id.btn_add);
        text_Status=(EditText) findViewById(R.id.text_Status);
     }
    public void onClick(View view) {
        Intent service_intent = new Intent(MainActivity.this,AddService.class);
        String s = text_Status.getText().toString();
        if (TextUtils.isEmpty(s)) {
            text_Status.setError(getString(R.string.errTvempty));
        }
        else {
        int number_input = Integer.parseInt(s);
        service_intent.putExtra("first",number_input);
//          prefer log.d from next time.
        Log.w("1","Calling Service from Activity-1");
        startService(service_intent);
        }
    }
}
