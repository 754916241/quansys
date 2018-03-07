package com.wyj.quansystem.controller;

import com.wyj.quansystem.bean.JobBean;
import com.wyj.quansystem.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * controller与responseBody的结合
 * 表示返回的为一个body类型的数据
 *
 */
@RestController
@RequestMapping("/jobController")
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping(value="addJob", method = RequestMethod.POST)
    public Map<String, Object> addJob(@RequestBody JobBean job){
        Map<String, Object> resultMap = new HashMap<>();
        if(jobService.insertJob(job)){
            resultMap.put("status", 0);
        }else{
            resultMap.put("status", 1);
        }
        return resultMap;

    }

    @RequestMapping(value="getJob", method = RequestMethod.GET)
    public Map<String, Object> getJobList(int jobStatus){
        Map<String, Object> resultMap = new HashMap<>();
        List<JobBean> jobList = jobService.getJobList(jobStatus);
        resultMap.put("jobData", jobList);
        return resultMap;
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
