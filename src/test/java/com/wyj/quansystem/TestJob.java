package com.wyj.quansystem;

import com.wyj.quansystem.bean.CompanyBean;
import com.wyj.quansystem.bean.JobBean;
import com.wyj.quansystem.service.CompanyService;
import com.wyj.quansystem.service.JobService;
import com.wyj.quansystem.service.impl.JobServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public class TestJob extends QuansystemApplicationTests{

    @Autowired
    private JobService service;

    @Autowired
    private CompanyService companyService;

    @Test
    public void testJobList() throws Exception {
        List<JobBean> list = service.getJobList(3);
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testJob(){
        CompanyBean companyBean = companyService.getInterview(1);
        System.out.println();
    }
}
