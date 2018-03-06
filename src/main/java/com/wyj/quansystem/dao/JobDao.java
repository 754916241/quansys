package com.wyj.quansystem.dao;

import com.wyj.quansystem.bean.JobBean;

import java.util.List;

public interface JobDao {

    /**
     * 查询出所有发布的工作职位
     */
    List<JobBean> queryAllJob();

    /**
     * 根据id查询工作职位
     */
    JobBean queryJobById(int jobId);

    /**
     * 添加新的职位
     */
    int insertJob(JobBean jobBean);

    /**
     * 删除职位
     */
    int deleteJob(int jobId);

    /**
     * 修改职位
     */
    int updateJob(JobBean jobBean);

    /**
     * 根据id修改状态
     */
}
