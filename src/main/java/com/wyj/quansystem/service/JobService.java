package com.wyj.quansystem.service;

import com.github.pagehelper.Page;
import com.wyj.quansystem.bean.JobBean;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


public interface JobService {

    boolean insertJob(JobBean job);

    List<JobBean> getJobList(int status, String token) throws Exception;

    boolean updateJobStatus(int jobId, int jobStatus);

    JobBean getJobDetail(int jobId);

    Page<JobBean> getJobPage(int status, int pageNum, int pageSize);
}
