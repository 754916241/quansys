package com.wyj.quansystem.bean;

import java.util.List;

public class CompanyBean {

    private int id;
    private String companyName;
    private String companyIntroduction;
    private String companyEmail;
    private List<DeliverBean> deliverBeanList;
    private List<JobBean> jobBeanList;

    public List<JobBean> getJobBeanList() {
        return jobBeanList;
    }

    public void setJobBeanList(List<JobBean> jobBeanList) {
        this.jobBeanList = jobBeanList;
    }


    public List<DeliverBean> getDeliverBeanList() {
        return deliverBeanList;
    }

    public void setDeliverBeanList(List<DeliverBean> deliverBeanList) {
        this.deliverBeanList = deliverBeanList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyIntroduction() {
        return companyIntroduction;
    }

    public void setCompanyIntroduction(String companyIntroduction) {
        this.companyIntroduction = companyIntroduction;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }
}
