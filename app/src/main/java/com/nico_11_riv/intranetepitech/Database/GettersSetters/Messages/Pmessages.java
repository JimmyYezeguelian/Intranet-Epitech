package com.nico_11_riv.intranetepitech.Database.GettersSetters.Messages;

import com.nico_11_riv.intranetepitech.Database.GettersSetters.User.GUser;
import com.nico_11_riv.intranetepitech.Database.Messages;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pmessages {
    public Pmessages(String api) {
        GUser user = new GUser();
        try {
            JSONArray messages = new JSONArray(api);
            for (int i = 0; i < messages.length(); ++i) {
                JSONObject tmp = messages.getJSONObject(i);
                Messages msg = new Messages(user.getToken());
                msg.setTitle(tmp.getString("title"));
                msg.setContent(tmp.getString("content"));
                msg.setDate(tmp.getString("date"));
                msg.setLogin(tmp.getJSONObject("user").getString("title"));
                msg.setPicture(tmp.getJSONObject("user").getString("picture"));
                msg.save();
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
