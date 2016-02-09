package com.nico_11_riv.intranetepitech.Database;

import com.orm.SugarRecord;
import com.nico_11_riv.intranetepitech.Database.GettersSetters.User.GUser;

public class Planning extends SugarRecord {
    private String token;
    private String start;
    private String end;
    private String eventreg;
    private String acti_title;
    private String codemodule;
    private String total_students_registered;
    private String codeacti;
    private String codeevent;
    private String codeinstance;
    private String scolaryear;
    private String semester;
    private String regstudent;
    private String allow_token;
    private String modulereg;
    private String titlemodule;

    public Planning() {
        GUser gUser = new GUser();
        this.token = gUser.getToken();
    }

    public Planning(String token) {
        this.token = token;
    }


    public String getTitlemodule() {
        return titlemodule;
    }

    public void setTitlemodule(String titlemodule) {
        this.titlemodule = titlemodule;
    }

    public String getActi_title() {
        return acti_title;
    }

    public void setActi_title(String acti_title) {
        this.acti_title = acti_title;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getEventreg() {
        return eventreg;
    }

    public void setEventreg(String eventreg) {
        this.eventreg = eventreg;
    }

    public String getCodemodule() {
        return codemodule;
    }

    public void setCodemodule(String codemodule) {
        this.codemodule = codemodule;
    }

    public String getTotal_students_registered() {
        return total_students_registered;
    }

    public void setTotal_students_registered(String total_students_registered) {
        this.total_students_registered = total_students_registered;
    }

    public String getCodeacti() {
        return codeacti;
    }

    public void setCodeacti(String codeacti) {
        this.codeacti = codeacti;
    }

    public String getCodeevent() {
        return codeevent;
    }

    public void setCodeevent(String codeevent) {
        this.codeevent = codeevent;
    }

    public String getCodeinstance() {
        return codeinstance;
    }

    public void setCodeinstance(String codeinstance) {
        this.codeinstance = codeinstance;
    }

    public String getScolaryear() {
        return scolaryear;
    }

    public void setScolaryear(String scolaryear) {
        this.scolaryear = scolaryear;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getRegister_student() {
        return regstudent;
    }

    public void setRegister_student(String register_student) {
        this.regstudent = register_student;
    }

    public String getAllow_token() {
        return allow_token;
    }

    public void setAllow_token(String allow_token) {
        this.allow_token = allow_token;
    }

    public String getModulereg() {
        return modulereg;
    }

    public void setModulereg(String modulereg) {
        this.modulereg = modulereg;
    }
}
