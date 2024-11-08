package com.mohit.SecondJobApp.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mohit.SecondJobApp.job.Job;
import com.mohit.SecondJobApp.job.JobService;

@Service
public class JobServiceImpl implements JobService {

	private List<Job> jobs = new ArrayList<>();
	private Long nextId = 1L;

	@Override
	public List<Job> findAll() {
		return jobs;
	}

	@Override
	public void createJob(Job job) {
		job.setId(nextId++);
		jobs.add(job);
	}

	@Override
	public Job getJobById(Long id) {
		for (Job job : jobs) {
			if (job.getId() == id)
				return job;
		}
		return null;
	}

	@Override
	public boolean deleteJobById(Long id) {
		for (Job job : jobs) {
			if (job.getId() == id) {
				jobs.remove(job);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateJobById(Long id, Job UpdatedJob) {
		for (Job job : jobs) {
			if (job.getId() == id) {
				job.setTitle(UpdatedJob.getTitle());
				job.setDescription(UpdatedJob.getDescription());
				job.setMinSalary(UpdatedJob.getMinSalary());
				job.setMaxSalary(UpdatedJob.getMaxSalary());
				job.setLocation(UpdatedJob.getLocation());
				return true;
			}
		}
		return false;
	}

}
