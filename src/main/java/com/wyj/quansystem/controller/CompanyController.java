package com.wyj.quansystem.controller;


import com.wyj.quansystem.bean.JobBean;
import com.wyj.quansystem.service.CompanyService;
import com.wyj.quansystem.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/companyController")
public class CompanyController {


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
}
