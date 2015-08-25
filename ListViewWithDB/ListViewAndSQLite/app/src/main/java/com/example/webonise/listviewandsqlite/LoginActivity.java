package com.example.webonise.listviewandsqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LoginActivity extends Activity implements View.OnClickListener {
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent HomeScreen = new Intent(LoginActivity.this,ListViewAdaptorHomeScreenActivity.class);
        startActivity(HomeScreen);
    }


}
