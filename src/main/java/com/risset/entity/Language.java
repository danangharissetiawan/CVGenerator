package com.risset.entity;

public class Language {
    private int id;
    private String language;
    private String level;
    private Personal personal;

    public Language() {
    }

    public Language(int id, String language, String level) {
        this.id = id;
        this.language = language;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

}
