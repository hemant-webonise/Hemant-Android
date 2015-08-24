package com.example.webonise.asynandvolley;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by webonise on 24/8/15.
 */
public class JsonAsyncTask extends AsyncTask<String,String,JSONObject> {
    public static final String NAME = "name";
    public static final String CONTACTS = "contacts";
    Activity activity;

    /*Constructor*/
    public JsonAsyncTask(HomeScreenActivity homeScreenActivity) {
        this.activity = homeScreenActivity;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        JsonParser jsonParser = new JsonParser();
        return jsonParser.getJSON(Constant.URL);

    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        try {
            GetList getList = new GetList(jsonObject).invoke();
            JSONArray jsonArray = getList.getJsonArray();
            String[] listData = getList.getData();
            getJSONItem(jsonArray, listData);
            getViewListView(listData);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void getViewListView(String[] listData) {
        ListView listView = (ListView) activity.findViewById(R.id.nameList);
        /*Used ArrayAdapter */
        ArrayAdapter arrayAdapter = new ArrayAdapter(activity, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(arrayAdapter);
    }

    private void getJSONItem(JSONArray jsonArray, String[] listData) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            listData[i] = jsonArray.getJSONObject(i).getString(NAME);
        }
    }

    private class GetList {
        private JSONObject jsonObject;
        private JSONArray jsonArray;
        private String[] data;

        public GetList(JSONObject jsonObject) {
            this.jsonObject = jsonObject;
        }

        public JSONArray getJsonArray() {
            return jsonArray;
        }

        public String[] getData() {
            return data;
        }

        public GetList invoke() throws JSONException {
            jsonArray = jsonObject.getJSONArray(CONTACTS);
            data = new String[jsonArray.length()];
            return this;
        }
    }
}
