package com.wyj.quansystem.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class CompanyBean implements Serializable{

    private static final long serialVersionUID = 4063730667413038803L;
    private int id;
    private String companyName;
    private String companyIntroduction;
    private String companyEmail;
    private List<DeliverBean> deliverBeanList;
    private List<JobBean> jobBeanList;


}
