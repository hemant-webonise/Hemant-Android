package com.example.webonise.asynandvolley;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by webonise on 24/8/15.
 */
public class DataGSON {
   /*Observe the JSON and create List for JSONArray*/
   private List<Contact> contacts;
    public List<Contact> getContacts() {
        return contacts;
    }
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}