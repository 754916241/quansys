package com.wyj.quansystem.service;

import com.wyj.quansystem.bean.JobBean;

import java.util.List;

public interface JobService {

    boolean insertJob(JobBean job);

    List<JobBean> getJobList(int status) throws Exception;

    boolean updateJobStatus(int jobId, int jobStatus);

    JobBean getJobDetail(int jobId);
}
