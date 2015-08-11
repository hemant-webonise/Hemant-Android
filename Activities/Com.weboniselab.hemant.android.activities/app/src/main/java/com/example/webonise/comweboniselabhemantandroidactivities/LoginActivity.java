package com.example.webonise.comweboniselabhemantandroidactivities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity implements View.OnClickListener {
    EditText edUsername;
    EditText edPassword;
    Button btnValidate;

    protected void initialize() {
        edUsername = (EditText) findViewById(R.id.et_username);
        edPassword = (EditText) findViewById(R.id.et_password);
        btnValidate = (Button) findViewById(R.id.btn_Validation);
//      Very Important We have to setOnclickListner on the btn.
        btnValidate.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("1","onCreate");
        setContentView(R.layout.activity_main);
        initialize();
    }




    @Override
    public void onClick(View view) {
        String uname = edUsername.getText().toString();
        String pass = edPassword.getText().toString();
                if (TextUtils.isEmpty(uname)) {
                    edUsername.setError(getString(R.string.err_usrName));
                }
                else if  (TextUtils.isEmpty(pass))
                {
                    edPassword.setError(getString(R.string.err_password));
                }
                else if (uname.equals(getString(R.string.usr_stored)) && pass.equals(getString(R.string.pass_stored))) {
                    Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
//                  PutExtra works in key value pair.
                    intent.putExtra(getString(R.string.usr_input), edUsername.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, getString(R.string.pass_incorect), Toast.LENGTH_LONG).show();
                }


    }




    @Override
    protected void onStart() {
        super.onStart();
        Log.w("2","onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.w("-","onReStart from OnStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("3","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("4","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("5","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("6","onDestroy");
    }
}




