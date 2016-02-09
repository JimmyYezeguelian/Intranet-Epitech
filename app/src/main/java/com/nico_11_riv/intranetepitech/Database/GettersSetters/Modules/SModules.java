package com.nico_11_riv.intranetepitech.Database.GettersSetters.Modules;


import com.nico_11_riv.intranetepitech.Database.GettersSetters.User.GUser;
import com.nico_11_riv.intranetepitech.Database.Modules;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SModules {
    public SModules(String api) {
        GUser user = new GUser();
        try {
            JSONObject obj = new JSONObject(api);
            JSONArray json = obj.getJSONArray("modules");
            for (int i = 0; i < json.length(); i++) {
                JSONObject tmp = json.getJSONObject(i);
                Modules modules = new Modules(user.getToken());
                modules.setScolaryear(tmp.getString("scolaryear"));
                modules.setCodemodule(tmp.getString("codemodule"));
                modules.setCodeinstance(tmp.getString("codeinstance"));
                modules.setTitle(tmp.getString("title"));
                modules.setId_instance(tmp.getString("id_instance"));
                modules.setDate_ins(tmp.getString("date_ins"));
                modules.setCycle(tmp.getString("cycle"));
                modules.setGrade(tmp.getString("grade"));
                modules.setCredits(tmp.getString("credits"));
                modules.setFlags(tmp.getString("flags"));
                modules.setBarrage(tmp.getString("barrage"));
                modules.setInstance_id(tmp.getString("instance_id"));
                modules.setModule_rating(tmp.getString("module_rating"));
                modules.setSemester(tmp.getString("semester"));
                modules.save();
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
