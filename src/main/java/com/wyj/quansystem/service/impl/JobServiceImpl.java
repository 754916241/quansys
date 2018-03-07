package com.wyj.quansystem.service.impl;

import com.wyj.quansystem.bean.JobBean;
import com.wyj.quansystem.dao.JobDao;
import com.wyj.quansystem.service.JobService;
import com.wyj.quansystem.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    @Transactional
    @Override
    public boolean insertJob(JobBean job) {
        job.setJobUpdateTime(Utils.date2Str(new Date()));
        int num = jobDao.insertJob(job);
        if(num > 0)
            return true;

        else
            throw new RuntimeException("插入失败");

    }

    @Override
    public List<JobBean> getJobList(int status) {
        //for(jobDao)
        return jobDao.queryAllJob(status);
    }



    @Override
    public boolean updateJobStatus(int jobId, int jobStatus) {
        int num = jobDao.updateJobStatus(jobId, jobStatus);
        return num > 0;
    }

    @Override
    public JobBean getJobDetail(int jobId) {
        return jobDao.queryJobById(jobId);
    }
}
