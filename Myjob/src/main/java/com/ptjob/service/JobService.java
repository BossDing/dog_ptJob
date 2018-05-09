package com.ptjob.service;

import java.util.List;

import com.ptjob.entity.Job;
import com.ptjob.entity.JobPage;
import com.ptjob.util.PageData;

public interface JobService {
	public boolean addJob(Job job);
	
	public Job getJobById(int id);
	
	public List<Job> getAllJobs();
	
	public List<Job> getJobsByUserName(String userName);
	
	public List<Job> getJobsByJobType(String jobType);
	
	public PageData<Job> getJobsByPage(int page, int pageSize,JobPage jp);
	/***
	 * ֹͣ��Ƹ
	 * @param jobId
	 * @return
	 */
	public boolean stopWanted(int jobId);
	/***
	 * ɾ������
	 * @param jobId
	 * @return
	 */
	public boolean deleteJob(int jobId);
	
	public boolean updateByPrimaryKeySelective(Job job);
	
}
