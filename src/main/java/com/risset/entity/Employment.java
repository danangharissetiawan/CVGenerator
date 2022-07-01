package com.risset.entity;

import java.util.Date;

public class Employment {
    private int id;
    private String position;
    private String employer;
    private String city;
    private Date startDate;
    private Date endDate;
    private Boolean isPresent;
    private String description;
    private String achievement;
    private Personal personal;

    public Employment() {
    }

    public Employment(int id, String position, String employer, String city, Date startDate, Date endDate, Boolean isPresent, String description, String achievement) {
        this.id = id;
        this.position = position;
        this.employer = employer;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isPresent = isPresent;
        this.description = description;
        this.achievement = achievement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(Boolean isPresent) {
        this.isPresent = isPresent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

}
