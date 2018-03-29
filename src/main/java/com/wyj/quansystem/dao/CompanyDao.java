package com.wyj.quansystem.dao;

import com.wyj.quansystem.bean.CompanyBean;

public interface CompanyDao {

    CompanyBean queryCompany(int id);

    CompanyBean getInterview(int id);
}
