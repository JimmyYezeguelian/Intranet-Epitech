package com.nico_11_riv.intranetepitech.Database;

import com.orm.SugarRecord;

public class Trombi extends SugarRecord {
    private String login;
    private String title;
    private String firstname;
    private String lastname;
    private String email;
    private String picture;
    private String promo;
    private String semester;
    private String location;
    private String course_code;
    private String studentyear;
    private String credits;
    private String average_gpa;

    public Trombi() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getStudentyear() {
        return studentyear;
    }

    public void setStudentyear(String studentyear) {
        this.studentyear = studentyear;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getAverage_gpa() {
        return average_gpa;
    }

    public void setAverage_gpa(String average_gpa) {
        this.average_gpa = average_gpa;
    }
}
