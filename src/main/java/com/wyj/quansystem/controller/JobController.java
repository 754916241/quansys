package com.wyj.quansystem.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.wyj.quansystem.bean.JobBean;
import com.wyj.quansystem.bean.ResultBean;
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

    @RequestMapping(value="addJob", method = RequestMethod.POST)
    // @Cacheable(cacheNames = "job", key = "123")
    public ResultBean addJob(@RequestBody JobBean job){
        if(jobService.insertJob(job)){
            return ResultUtils.success();
        }
        return ResultUtils.error();
    }

    @RequestMapping(value="getJob", method = RequestMethod.GET)
    public ResultBean<List<JobBean>> getJobList(int jobStatus, HttpServletRequest request) throws Exception {
        String token = CookieUtils.getCookieValue(request, Constant.token);
        /* Map<String, Object> resultMap = new HashMap<>();*/
        List<JobBean> jobList = jobService.getJobList(jobStatus, token);
        return ResultUtils.success(jobList);
    }

    @RequestMapping(value="getJobPage", method = RequestMethod.GET)
    public ResultBean getJobPage(int jobStatus, int page, int pageSize) throws Exception {
        //其实这个page继承自ArrayList
        Page<JobBean> jobList = jobService.getJobPage(jobStatus, page, pageSize);
        PageInfo<JobBean> pageInfo = new PageInfo<>(jobList);
        ResultBean<PageInfo<JobBean>> resultBean = ResultUtils.success(pageInfo);
        return resultBean;
    }

    @RequestMapping(value="getJobDetail", method = RequestMethod.GET)
    public Map<String, Object> getJobDetail(int id){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            JobBean JobBean = jobService.getJobDetail(id);
            resultMap.put("job", JobBean);
            resultMap.put("status", 200);
        } catch (Exception e) {
            resultMap.put("status", 400);
        }
        return resultMap;
    }

    @RequestMapping(value="changeJobStatus", method = RequestMethod.POST)
    public Map<String, Object> changeJobStatus(@RequestBody JobBean job){
        Map<String, Object> resultMap = new HashMap<>();
        int status = job.getJobStatus();
        if(jobService.updateJobStatus(job.getId(), status)){
            resultMap.put("status", 200);
        }else{
            resultMap.put("status", 400);
        }
        return resultMap;
    }



}
