package com.risset.entity;

public class Award {
    private int id;
    private String title;
    private String appreciator;
    private Personal personal;

    public Award() {
    }

    public Award(int id, String title, String appreciator) {
        this.id = id;
        this.title = title;
        this.appreciator = appreciator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAppreciator() {
        return appreciator;
    }

    public void setAppreciator(String appreciator) {
        this.appreciator = appreciator;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

}
