package com.wyj.quansystem.service;


import com.wyj.quansystem.bean.CompanyBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface CompanyService {

    boolean acceptImage(MultipartFile file, HttpServletRequest request) throws IOException;

    CompanyBean getInterview(int companyId);

}
