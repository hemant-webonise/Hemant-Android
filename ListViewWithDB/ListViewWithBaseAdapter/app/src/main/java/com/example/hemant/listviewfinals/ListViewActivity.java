package com.example.hemant.listviewfinals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class ListViewActivity extends Activity implements View.OnClickListener, PersonListAdapter.CallBack {
    private Button btnAdd;
    private ListView listView;
    private PersonListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initializeViews();
    }

    public void initializeViews() {
        btnAdd = (Button) findViewById(R.id.btnSave);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DetailsDBAdapter personDatabaseHelper = new DetailsDBAdapter(this);
        listView = (ListView) findViewById(R.id.lvDetailsList);
        listAdapter = new PersonListAdapter(this, R.layout.person_list_item, personDatabaseHelper.fetchAllDetails());
        listView.setAdapter(listAdapter);
        personDatabaseHelper.close();

    }

    @Override
    public void onDeleted() {
        DetailsDBAdapter personDatabaseHelper = new DetailsDBAdapter(this);
        listView = (ListView) findViewById(R.id.lvDetailsList);
        listAdapter = new PersonListAdapter(this, R.layout.person_list_item, personDatabaseHelper.fetchAllDetails());
        listView.setAdapter(listAdapter);
        personDatabaseHelper.close();

    }
}
