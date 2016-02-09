package com.nico_11_riv.intranetepitech.Database.GettersSetters.Trombi;

import com.nico_11_riv.intranetepitech.Database.Trombi;

import org.json.JSONException;
import org.json.JSONObject;

public class STrombi {
    public STrombi(String api) {
        try {
            JSONObject json = new JSONObject(api);
            if (json.has("login")) {
                Trombi trombi = new Trombi();
                trombi.setLogin(json.getString("login"));
                trombi.setTitle(json.getString("title"));
                trombi.setFirstname(json.getString("firstname"));
                trombi.setLastname(json.getString("lastname"));
                trombi.setEmail(json.getString("internal_email"));
                trombi.setPicture(json.getString("picture"));
                trombi.setPromo(json.getString("promo"));
                trombi.setSemester(json.getString("semester"));
                trombi.setLocation(json.getString("location"));
                trombi.setCourse_code(json.getString("course_code"));
                trombi.setStudentyear(json.getString("studentyear"));
                trombi.setCredits(json.getString("credits"));
                trombi.setAverage_gpa(json.getJSONArray("averageGPA").getJSONObject(0).getString("gpa_average"));
                trombi.save();
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
