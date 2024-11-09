package com.mohit.SecondJobApp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.SecondJobApp.job.Job;
import com.mohit.SecondJobApp.job.JobRepository;
import com.mohit.SecondJobApp.job.JobService;

@Service
public class JobServiceImpl implements JobService {

	// private List<Job> jobs = new ArrayList<>();
	// private Long nextId = 1L;

	@Autowired
	private JobRepository jobRepository;

	@Override
	public List<Job> findAll() {
		return jobRepository.findAll();
	}

	@Override
	public void createJob(Job job) {
		jobRepository.save(job);
	}

	@Override
	public Job getJobById(Long id) {
		return jobRepository.findById(id).orElse(null);

	}

	@Override
	public boolean deleteJobById(Long id) {
		if (getJobById(id) == null)
			return false;
		else {
			jobRepository.deleteById(id);
			return true;
		}
	}

	@Override
	public boolean updateJobById(Long id, Job UpdatedJob) {
		if (getJobById(id) != null) {
			Job job = getJobById(id);
			job.setTitle(UpdatedJob.getTitle());
			job.setDescription(UpdatedJob.getDescription());
			job.setMinSalary(UpdatedJob.getMinSalary());
			job.setMaxSalary(UpdatedJob.getMaxSalary());
			job.setLocation(UpdatedJob.getLocation());
			jobRepository.save(job);
			return true;
		}

		return false;

	}

}
