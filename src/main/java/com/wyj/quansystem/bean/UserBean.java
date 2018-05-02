package com.wyj.quansystem.bean;

import java.io.Serializable;

public class UserBean implements Serializable{

    private static final long serialVersionUID = 4116963110577399657L;
    private String username, password, userType;
    private int id;
    private CompanyBean company;

    public UserBean() {
    }

    public UserBean(int id, String userType) {
        this.userType = userType;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CompanyBean getCompany() {
        return company;
    }

    public void setCompany(CompanyBean company) {
        this.company = company;
    }
}
