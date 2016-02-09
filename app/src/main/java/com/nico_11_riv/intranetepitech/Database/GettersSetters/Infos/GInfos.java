package com.nico_11_riv.intranetepitech.Database.GettersSetters.Infos;

import com.nico_11_riv.intranetepitech.Database.GettersSetters.User.GUser;
import com.nico_11_riv.intranetepitech.Database.Infos;

import java.util.List;

public class GInfos {
    private String token;
    private String ip;
    private String uid;
    private String title;
    private String firstname;
    private String lastname;
    private String picture;
    private String promo;
    private String semester;
    private String credits;
    private String gpa;
    private String credits_min;
    private String credits_norm;
    private String credits_obj;
    private String active_log;
    private String email;

    public GInfos() {
        GUser gUser = new GUser();
        String utoken = gUser.getToken();

        if (utoken != null) {
            List<Infos> infos = Infos.find(Infos.class, "token = ?", utoken);
            Infos info = infos.get(0);
            this.token = info.getToken();
            this.ip = info.getIp();
            this.uid = info.getUid();
            this.title = info.getTitle();
            this.firstname = info.getFirstname();
            this.lastname = info.getLastname();
            this.picture = info.getPicture();
            this.promo = info.getPromo();
            this.semester = info.getSemester();
            this.credits_min = info.getCredits_min();
            this.credits_norm = info.getCredits_norm();
            this.credits_obj = info.getCredits_obj();
            this.active_log = info.getActive_log();
            this.email = info.getEmail();
            this.credits = info.getCredits();
            this.gpa = info.getGpa();
        }
    }

    public String getToken() {
        return token;
    }

    public String getIp() {
        return ip;
    }

    public String getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPicture() {
        return picture;
    }

    public String getPromo() {
        return promo;
    }

    public String getSemester() {
        return semester;
    }

    public String getCredits_obj() {
        return credits_obj;
    }

    public String getCredits_min() {
        return credits_min;
    }

    public String getCredits_norm() {
        return credits_norm;
    }

    public String getActive_log() {
        return active_log;
    }

    public String getEmail() { return email; }

    public String getCredits() {
        return credits;
    }

    public String getGpa() {
        return gpa;
    }
}
