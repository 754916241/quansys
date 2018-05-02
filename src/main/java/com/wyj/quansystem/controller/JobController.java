package com.wyj.quansystem.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.wyj.quansystem.annotation.TokenValue;
import com.wyj.quansystem.bean.JobBean;
import com.wyj.quansystem.bean.ResultBean;
import com.wyj.quansystem.component.RedisComponent;
import com.wyj.quansystem.exception.ResultException;
import com.wyj.quansystem.service.JobService;
import com.wyj.quansystem.util.Constant;
import com.wyj.quansystem.util.CookieUtils;
import com.wyj.quansystem.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * controller与responseBody的结合
 * 表示返回的为一个body类型的数据
 *
 */
@RestController
@RequestMapping("/jobController")
@Slf4j
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private RedisComponent redisComponent;

    @PostMapping(value="addJob")
    public ResultBean addJob(@RequestBody JobBean job, @TokenValue String token){
        int companyId = redisComponent.getIdFromToken(token);
        job.setCompanyId(companyId);
        if(jobService.insertJob(job)){
            return ResultUtils.success();
        }
        return ResultUtils.error();
    }

    @GetMapping(value="getJobPage")
    public ResultBean getJobPage(int jobStatus, int page, int pageSize, @TokenValue String token){
        log.info(token);
        int companyId = redisComponent.getIdFromToken(token);
        //其实这个page继承自ArrayList
        Page<JobBean> jobList = jobService.getJobPage(jobStatus, page, pageSize, companyId);
        PageInfo<JobBean> pageInfo = new PageInfo<>(jobList);
        return ResultUtils.success(pageInfo);
    }

    @GetMapping(value="getJobDetail")
    public ResultBean getJobDetail(int id){
        JobBean JobBean = jobService.getJobDetail(id);
        return ResultUtils.success(JobBean);
    }

    @PostMapping(value="changeJobStatus")
    public ResultBean changeJob(@RequestBody JobBean job){
        jobService.updateJobStatus(job.getId(), job.getJobStatus());
        return ResultUtils.success();
    }
}
