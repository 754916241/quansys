package com.wyj.quansystem.bean;

import com.fasterxml.jackson.annotation.JsonFilter;

import java.util.Date;

public class JobBean {

    private int id;
    private String jobName;
    private String jobCatagory;
    private String jobCity;
    private int jobPeopleNumber;
    private String jobInducement;
    private String jobDescription;
    private String jobAddress;
    private int jobLowSalary;
    private int jobHighSalary;
    private String jobProperty;
    private String jobDegree;
    private String jobEmail;
    private String jobExperience;
    private int jobAppluNum;
    private String jobUpdateTime;

    public int getJobAppluNum() {
        return jobAppluNum;
    }

    public void setJobAppluNum(int jobAppluNum) {
        this.jobAppluNum = jobAppluNum;
    }

    public String getJobUpdateTime() {
        return jobUpdateTime;
    }

    public void setJobUpdateTime(String jobUpdateTime) {
        this.jobUpdateTime = jobUpdateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobCatagory() {
        return jobCatagory;
    }

    public void setJobCatagory(String jobCatagory) {
        this.jobCatagory = jobCatagory;
    }

    public String getJobCity() {
        return jobCity;
    }

    public void setJobCity(String jobCity) {
        this.jobCity = jobCity;
    }

    public int getJobPeopleNumber() {
        return jobPeopleNumber;
    }

    public void setJobPeopleNumber(int jobPeopleNumber) {
        this.jobPeopleNumber = jobPeopleNumber;
    }

    public String getJobInducement() {
        return jobInducement;
    }

    public void setJobInducement(String jobInducement) {
        this.jobInducement = jobInducement;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    public int getJobLowSalary() {
        return jobLowSalary;
    }

    public void setJobLowSalary(int jobLowSalary) {
        this.jobLowSalary = jobLowSalary;
    }

    public int getJobHighSalary() {
        return jobHighSalary;
    }

    public void setJobHighSalary(int jobHighSalary) {
        this.jobHighSalary = jobHighSalary;
    }

    public String getJobProperty() {
        return jobProperty;
    }

    public void setJobProperty(String jobProperty) {
        this.jobProperty = jobProperty;
    }

    public String getJobDegree() {
        return jobDegree;
    }

    public void setJobDegree(String jobDegree) {
        this.jobDegree = jobDegree;
    }

    public String getJobEmail() {
        return jobEmail;
    }

    public void setJobEmail(String jobEmail) {
        this.jobEmail = jobEmail;
    }

    public String getJobExperience() {
        return jobExperience;
    }

    public void setJobExperience(String jobExperience) {
        this.jobExperience = jobExperience;
    }
}
