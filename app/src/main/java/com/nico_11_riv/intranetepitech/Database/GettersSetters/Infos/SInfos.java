package com.nico_11_riv.intranetepitech.Database.GettersSetters.Infos;

import com.google.gson.Gson;
import com.nico_11_riv.intranetepitech.Database.GettersSetters.Marks.SMarks;
import com.nico_11_riv.intranetepitech.Database.GettersSetters.User.GUser;
import com.nico_11_riv.intranetepitech.Database.Infos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SInfos {
    public SInfos(String infos, String trombi) {
        if (infos != null && trombi != null) {
            JSONObject jsobj;
            JSONObject tr;
            GUser gUser = new GUser();
            try {
                jsobj = new JSONObject(infos);
                tr = new JSONObject(trombi);
                Infos ip = new Infos();
                ip.setCredits(tr.getString("credits"));
                JSONArray g = tr.getJSONArray("gpa");
                ip.setGpa(g.getJSONObject(0).getString("gpa"));
                ip.setIp(jsobj.getString("ip"));
                JSONObject infs = jsobj.getJSONObject("infos");
                ip.setUid(infs.getString("id"));
                ip.setTitle(infs.getString("title"));
                ip.setFirstname(infs.getString("firstname"));
                ip.setLastname(infs.getString("lastname"));
                ip.setPicture("https://cdn.local.epitech.eu/userprofil/profilview/" + infs.getString("picture").replace(".bmp", ".jpg"));
                ip.setPromo(infs.getString("promo"));
                ip.setSemester(infs.getString("semester"));
                ip.setEmail(gUser.getLogin() + "@epitech.eu");
                try {
                    JSONArray current = jsobj.getJSONArray("current");
                    JSONObject curr = current.getJSONObject(0);
                    ip.setCredits_min(curr.getString("credits_norm"));
                    ip.setCredits_norm(curr.getString("credits_norm"));
                    ip.setCredits_obj(curr.getString("credits_obj"));
                    ip.setActive_log(curr.getString("active_log").split("\\.")[0]);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    SCurrent_Projets current_projets = new SCurrent_Projets(jsobj.getJSONObject("board"), gUser.getToken());
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                ip.save();
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
