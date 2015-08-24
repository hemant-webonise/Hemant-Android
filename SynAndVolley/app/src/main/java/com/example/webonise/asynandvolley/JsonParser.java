package com.example.webonise.asynandvolley;


import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class JsonParser {
    static InputStream is = null;
    static JSONObject jsonObject = null;
    static String jsonString = null;

    /*Contructor */
    public JsonParser() {
    }

    public JSONObject getJSON(String url) {

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            StatusLine statusLine = httpResponse.getStatusLine();

            int code = statusLine.getStatusCode();

            if (code == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                setJSONObject();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

      /*Return JSON String*/
        return jsonObject;

    }

    private void setJSONObject() throws IOException, JSONException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        jsonString = stringBuilder.toString();
        jsonObject = new JSONObject(jsonString);
    }
}
