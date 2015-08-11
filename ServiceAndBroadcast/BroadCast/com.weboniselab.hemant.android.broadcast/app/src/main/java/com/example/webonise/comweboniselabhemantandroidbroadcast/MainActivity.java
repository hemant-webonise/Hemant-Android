package com.example.webonise.comweboniselabhemantandroidbroadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {
    Button startReceiver;
    EditText status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
       startReceiver = (Button) findViewById(R.id.btn_receiver);
       status = (EditText) findViewById(R.id.et_status);
       startReceiver.setOnClickListener(this);

}

    public void onClick(View view) {
        String s = status.getText().toString();
        Intent br_intent =getIntent();
        Bundle bundleStore = new Bundle();
        bundleStore= br_intent.getExtras();

        String input = null;
        try {
            input = bundleStore.getString("message");
            status.setText(input);
        } catch (Exception e) {
            e.printStackTrace();
            status.setText(getString(R.string.no_sms));
        }

    }
}
