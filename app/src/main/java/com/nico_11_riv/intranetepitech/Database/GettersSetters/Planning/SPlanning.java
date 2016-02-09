package com.nico_11_riv.intranetepitech.Database.GettersSetters.Planning;

/**
 * Created by victor on 19/01/2016.
 */

import com.nico_11_riv.intranetepitech.Database.GettersSetters.User.GUser;
import com.nico_11_riv.intranetepitech.Database.Planning;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


public class SPlanning {
    public SPlanning(String api) {
        GUser user = new GUser();
        try {
            JSONArray planning = new JSONArray(api);
            for (int i = 0; i < planning.length(); ++i) {
                JSONObject tmp = planning.getJSONObject(i);
                if (tmp.has("module_registered")) {
                    Planning pl = new Planning(user.getToken());
                    pl.setStart(tmp.getString("start"));
                    pl.setEnd(tmp.getString("end"));
                    pl.setEventreg(tmp.getString("event_registered"));
                    pl.setActi_title(tmp.getString("acti_title"));
                    pl.setCodemodule(tmp.getString("codemodule"));
                    pl.setTotal_students_registered(tmp.getString("total_students_registered"));
                    pl.setCodeacti(tmp.getString("codeacti"));
                    pl.setCodeevent(tmp.getString("codeevent"));
                    pl.setCodeinstance(tmp.getString("codeinstance"));
                    pl.setScolaryear(tmp.getString("scolaryear"));
                    pl.setSemester(tmp.getString("semester"));
                    pl.setRegister_student(tmp.getString("register_student"));
                    pl.setAllow_token(tmp.getString("allow_token"));
                    pl.setModulereg(tmp.getString("module_registered"));
                    pl.setTitlemodule(tmp.getString("titlemodule"));
                    pl.save();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}