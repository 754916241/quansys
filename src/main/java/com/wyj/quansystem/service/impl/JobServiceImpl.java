package com.wyj.quansystem.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wyj.quansystem.bean.JobBean;
import com.wyj.quansystem.dao.JobDao;
import com.wyj.quansystem.enums.ResultEnum;
import com.wyj.quansystem.exception.ResultException;
import com.wyj.quansystem.service.JobService;
import com.wyj.quansystem.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean insertJob(JobBean job) {
        //处理高并发情况下的分布式数据不同步问题   redis分布锁
        //加锁
        job.setJobUpdateTime(DateUtils.date2Str(new Date()));
        if(jobDao.insertJob(job) > 0)
            return true;
        else
            throw new ResultException(ResultEnum.DataBaseError);

    }

    @Override
    public boolean updateJobStatus(int jobId, int jobStatus) {
        int num = jobDao.updateJobStatus(jobId, jobStatus, DateUtils.date2Str(new Date()));
        if(num > 0){
            return true;
        }else{
            throw new ResultException(ResultEnum.DataBaseError);
        }
    }

    @Override
    public JobBean getJobDetail(int jobId) {
        return jobDao.queryJobById(jobId);
    }

    @Override
    public Page<JobBean> getJobPage(int status, int pageNum, int pageSize, int companyId) {
        PageHelper.startPage(pageNum, pageSize);
        return jobDao.queryAll(status, companyId);
    }
}
