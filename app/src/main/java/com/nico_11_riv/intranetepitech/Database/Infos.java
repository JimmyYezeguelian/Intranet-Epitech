package com.nico_11_riv.intranetepitech.Database;

import com.nico_11_riv.intranetepitech.Database.GettersSetters.User.GUser;
import com.orm.SugarRecord;

public class Infos extends SugarRecord {
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

    public Infos() {
        GUser gUser = new GUser();
        this.token = gUser.getToken();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCredits_obj() {
        return credits_obj;
    }

    public void setCredits_obj(String credits_obj) {
        this.credits_obj = credits_obj;
    }

    public String getCredits_norm() {
        return credits_norm;
    }

    public void setCredits_norm(String credits_norm) {
        this.credits_norm = credits_norm;
    }

    public String getCredits_min() {
        return credits_min;
    }

    public void setCredits_min(String credits_min) {
        this.credits_min = credits_min;
    }

    public String getActive_log() {
        return active_log;
    }

    public void setActive_log(String active_log) {
        this.active_log = active_log;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }
}
