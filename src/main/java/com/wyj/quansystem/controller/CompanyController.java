package com.wyj.quansystem.controller;


import com.wyj.quansystem.bean.CompanyBean;
import com.wyj.quansystem.bean.JobBean;
import com.wyj.quansystem.bean.ResultBean;
import com.wyj.quansystem.bean.UserBean;
import com.wyj.quansystem.service.CompanyService;
import com.wyj.quansystem.service.JobService;
import com.wyj.quansystem.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/companyController")
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @PostMapping(value="uploadImage")
    public Map<String, Object> acceptImage(@RequestParam MultipartFile file, HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if(companyService.acceptImage(file, request)){
                resultMap.put("status", 200);
                //resultMap.put("address")
            }else {
                resultMap.put("status", 400);
                resultMap.put("msg", "上传文件为空，请检查后重试");
            }
        } catch (IOException e) {
            resultMap.put("status", 400);
            resultMap.put("msg", e.getMessage());
        }

        return resultMap;
    }

    @GetMapping(value = "getInterview")
    public ResultBean<CompanyBean> getInterview(HttpServletRequest request){
        Integer companyId = (Integer) request.getSession().getAttribute("id");
        logger.info(companyId+"");
        ResultBean<CompanyBean> resultBean = ResultUtils.success(companyService.getInterview(companyId));
        return resultBean;
    }
}
