package com.wyj.quansystem.bean;

public class InterviewBean {

    private int id;
    private String interviewTime;
    private CVBean cvBean;
    private JobBean jobBean;

    public JobBean getJobBean() {
        return jobBean;
    }

    public void setJobBean(JobBean jobBean) {
        this.jobBean = jobBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    public CVBean getCvBean() {
        return cvBean;
    }

    public void setCvBean(CVBean cvBean) {
        this.cvBean = cvBean;
    }
}
