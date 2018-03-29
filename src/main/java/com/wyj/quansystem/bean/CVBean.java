package com.wyj.quansystem.bean;

public class CVBean {

    private int id;
    private String cvName;
    private InterviewBean interviewBean;

    public InterviewBean getInterviewBean() {
        return interviewBean;
    }

    public void setInterviewBean(InterviewBean interviewBean) {
        this.interviewBean = interviewBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
    }
}
