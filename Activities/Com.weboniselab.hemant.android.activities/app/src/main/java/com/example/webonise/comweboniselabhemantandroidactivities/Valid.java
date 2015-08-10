package com.example.webonise.comweboniselabhemantandroidactivities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by webonise on 10/8/15.
 */
public class Valid extends Activity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Log.w("1-","onCreate");
        setContentView(R.layout.success);
        TextView tv = (TextView) findViewById(R.id.tv_Validity);
//      When does String Format make sense?
        tv.setText(String.format(getString(R.string.Welcome), getIntent().getExtras().getString(getString(R.string.User_onValidate))));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w("2-", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.w("--","onReStart from OnStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("3-","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("4-","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("5-","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("6-","onDestroy");
    }
}