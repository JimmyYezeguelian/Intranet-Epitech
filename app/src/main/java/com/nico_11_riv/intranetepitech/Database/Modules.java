package com.nico_11_riv.intranetepitech.Database;

import com.nico_11_riv.intranetepitech.Database.GettersSetters.User.GUser;
import com.orm.SugarRecord;

public class Modules extends SugarRecord {
    private String token;
    private String scolaryear;
    private String codemodule;
    private String codeinstance;
    private String title;
    private String id_instance;
    private String date_ins;
    private String cycle;
    private String grade;
    private String credits;
    private String flags;
    private String barrage;
    private String instance_id;
    private String module_rating;
    private String semester;

    public Modules() {
        GUser user = new GUser();
        this.token = user.getToken();
    }

    public Modules(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getScolaryear() {
        return scolaryear;
    }

    public void setScolaryear(String scolaryear) {
        this.scolaryear = scolaryear;
    }

    public String getCodemodule() {
        return codemodule;
    }

    public void setCodemodule(String codemodule) {
        this.codemodule = codemodule;
    }

    public String getCodeinstance() {
        return codeinstance;
    }

    public void setCodeinstance(String codeinstance) {
        this.codeinstance = codeinstance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId_instance() {
        return id_instance;
    }

    public void setId_instance(String id_instance) {
        this.id_instance = id_instance;
    }

    public String getDate_ins() {
        return date_ins;
    }

    public void setDate_ins(String date_ins) {
        this.date_ins = date_ins;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getBarrage() {
        return barrage;
    }

    public void setBarrage(String barrage) {
        this.barrage = barrage;
    }

    public String getInstance_id() {
        return instance_id;
    }

    public void setInstance_id(String instance_id) {
        this.instance_id = instance_id;
    }

    public String getModule_rating() {
        return module_rating;
    }

    public void setModule_rating(String module_rating) {
        this.module_rating = module_rating;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
