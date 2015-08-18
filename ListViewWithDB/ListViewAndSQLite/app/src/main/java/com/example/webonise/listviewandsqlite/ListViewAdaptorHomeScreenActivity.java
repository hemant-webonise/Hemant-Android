package com.example.webonise.listviewandsqlite;

import android.content.Intent;
import android.os.Bundle;


import android.app.Activity;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewAdaptorHomeScreenActivity extends Activity implements View.OnClickListener {

    Button btnSave;
    DetailsDBAdapter dbHelper;
    SimpleCursorAdapter dataAdapter;
    ListView listView;
    EditText myFilter;
    ImageButton deletor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Init();
        dbHelper.deleteAllDetails();
        dbHelper.createDetails(getString(R.string.Defualt), getString(R.string.DefualtAge), getString(R.string.DefualtHeight), getString(R.string.DefualtWeight));
        dbHelper.createDetails(getString(R.string.Defualt), getString(R.string.DefualtAge), getString(R.string.DefualtHeight), getString(R.string.DefualtWeight));
        dbHelper.createDetails(getString(R.string.Defualt), getString(R.string.DefualtAge), getString(R.string.DefualtHeight), getString(R.string.DefualtWeight));
        //Generate ListView from SQLite Database
        displayListView();
    }

    private void Init() {
        listView = (ListView) findViewById(R.id.listView1);
        dbHelper = new DetailsDBAdapter(this);
        dbHelper.open();
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        deletor = (ImageButton) findViewById(R.id.deletor);
        myFilter = (EditText) findViewById(R.id.myFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayListView();
    }

    private void displayListView() {
        Cursor cursor = dbHelper.fetchAllDetails();
        // The desired columns to be bound
        String[] columns = new String[]{  DetailsDBAdapter.COLUMN_NAME,  DetailsDBAdapter.COLUMN_AGE, DetailsDBAdapter.COLUMN_HEIGHT, DetailsDBAdapter.COLUMN_WEIGHT };

        // the XML defined views which the data will be bound to
        int[] to = new int[]{R.id.tvName, R.id.tvAge, R.id.tvHieght, R.id.tvWeight,};

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(this, R.layout.activity_android_list_view_details_adaptor, cursor, columns, to, 0);
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DetailsDBAdapter.COLUMN_NAME));
                Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> listView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),getString(R.string.longPressed),Toast.LENGTH_LONG).show();
                dbHelper.deleteCertainDetail((int) id);
                displayListView();
                return false;
            }
        });

        myFilter.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });

        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return dbHelper.fetchDetailsByName((constraint.toString()));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave :
            Toast.makeText(getApplicationContext(), getString(R.string.Clicked), Toast.LENGTH_SHORT).show();
            Intent toSave = new Intent(ListViewAdaptorHomeScreenActivity.this, DetailsActivity.class);
            startActivity(toSave);
            break;
            case R.id.deletor :
            Toast.makeText(getApplicationContext(),getString(R.string.deletor), Toast.LENGTH_SHORT).show();
            break;
    }
    }
}