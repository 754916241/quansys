package com.wyj.quansystem.service.impl;


import com.wyj.quansystem.bean.JobBean;
import com.wyj.quansystem.dao.CompanyDao;
import com.wyj.quansystem.dao.JobDao;
import com.wyj.quansystem.service.CompanyService;
import com.wyj.quansystem.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

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
}
