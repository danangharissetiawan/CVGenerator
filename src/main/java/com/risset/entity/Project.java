package com.risset.entity;

public class Project {
    private int id;
    private String name;
    private String utilized;
    private String inovation;
    private String description;
    private Personal personal;

    public Project() {
    }

    public Project(int id, String name, String utilized, String inovation, String description) {
        this.id = id;
        this.name = name;
        this.utilized = utilized;
        this.inovation = inovation;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUtilized() {
        return utilized;
    }

    public void setUtilized(String utilized) {
        this.utilized = utilized;
    }

    public String getInovation() {
        return inovation;
    }

    public void setInovation(String inovation) {
        this.inovation = inovation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

}
