package com.risset.entity;

import java.util.Date;

public class Education {
    private int id;
    private String schoolName;
    private Date startDate;
    private Date endDate;
    private Boolean isPresent;
    private String description;
    private String major;
    private String certificate;
    private String coursework;

    private Personal personal;

    public Education() {
    }

    public Education(int id, String schoolName, Date startDate, Date endDate, Boolean isPresent, String description, String major, String certificate, String coursework) {
        this.id = id;
        this.schoolName = schoolName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isPresent = isPresent;
        this.description = description;
        this.major = major;
        this.certificate = certificate;
        this.coursework = coursework;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getCoursework() {
        return coursework;
    }

    public void setCoursework(String coursework) {
        this.coursework = coursework;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }


}
