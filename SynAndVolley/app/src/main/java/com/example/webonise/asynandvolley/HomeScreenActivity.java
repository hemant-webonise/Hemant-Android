package com.example.webonise.asynandvolley;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;



public class HomeScreenActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*Json response from async task*/

        /* new JsonAsyncTask(this).execute();*/

        /*Json response from volley*/


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                /*Use of Gson */
                DataGSON jsonDataParser = new Gson().fromJson(jsonObject.toString(), DataGSON.class);

                String[] Data = new String[0];
                try {
                    Data = new String[jsonObject.getJSONArray(Constant.CONTACT).length()];
                    /*puts the items in list - Data*/
                    getJSONItem(jsonObject, jsonDataParser, Data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.w(getString(R.string.NameLog), jsonDataParser.getContacts().get(0).getName());
                 /*puts the Data in listView*/
                init(Data);
            }
            },
                new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                Log.d(getClass().getSimpleName(), volleyError.toString());
                }
            }
        );
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    private void init(String[] listData) {
        ListView listView = (ListView) findViewById(R.id.nameList);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(arrayAdapter);
    }

    private void getJSONItem(JSONObject jsonObject, DataGSON jsonDataParser, String[] listData) throws JSONException {
        for (int i = 0; i < jsonObject.getJSONArray(Constant.CONTACT).length(); i++) {
            listData[i] = jsonDataParser.getContacts().get(i).getName();
        }
    }
}
