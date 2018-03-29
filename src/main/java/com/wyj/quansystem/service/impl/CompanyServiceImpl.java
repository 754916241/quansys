package com.wyj.quansystem.service.impl;


import com.wyj.quansystem.bean.CompanyBean;
import com.wyj.quansystem.dao.CompanyDao;
import com.wyj.quansystem.enums.ResultEnum;
import com.wyj.quansystem.exception.ResultException;
import com.wyj.quansystem.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyDao companyDao;

    @Override
    public boolean acceptImage(MultipartFile file, HttpServletRequest request) throws IOException {
        if(!file.isEmpty()){
            String fileName = file.getOriginalFilename();
            String filePath = request.getSession().getServletContext().getRealPath("image/");
            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(filePath+fileName);
            out.write(file.getBytes());
            out.flush();
            out.close();
            //Files.copy(file.getInputStream(), Paths.get("upload-dir", file.getOriginalFilename()));
            return true;
        }

        return false;
    }

    @Override
    public CompanyBean getInterview(int companyId) {
        if(companyDao.getInterview(companyId) != null){
            return companyDao.getInterview(companyId);
        }else{
            throw new ResultException(ResultEnum.FailOne);
        }
    }
}
