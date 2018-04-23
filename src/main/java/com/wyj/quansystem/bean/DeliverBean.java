package com.wyj.quansystem.bean;

import java.io.Serializable;

public class DeliverBean implements Serializable{

    private static final long serialVersionUID = -3478445539271203431L;
    private int id;
    private String deliverTime;
    private String deliverStatus;
    private CVBean cvBean;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(String deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    public CVBean getCvBean() {
        return cvBean;
    }

    public void setCvBean(CVBean cvBean) {
        this.cvBean = cvBean;
    }
}

