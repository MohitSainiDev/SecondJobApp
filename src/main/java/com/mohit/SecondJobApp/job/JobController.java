package com.mohit.SecondJobApp.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/jobs")
public class JobController {

	@Autowired
	private JobService jobService;


	@GetMapping
	public ResponseEntity<List<Job>> findAll() {
		return ResponseEntity.ok(jobService.findAll());

	}

	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		jobService.createJob(job);
		return new ResponseEntity<>("Job created Successfully", HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id) {

		Job job = jobService.getJobById(id);
		if (job != null)
			return new ResponseEntity<>(job, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJobById(@PathVariable Long id) {

		boolean value = jobService.deleteJobById(id);
		if (value)
			return new ResponseEntity<>("Job deleted Successfully", HttpStatus.OK);
		return new ResponseEntity<>("Job Not found", HttpStatus.NOT_FOUND);
	}

	// @RequestMapping(value ="/jobs/{id}" ,method = RequestMethod.PUT )
	@PutMapping("/{id}")
	public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job job) {

		boolean value = jobService.updateJobById(id, job);
		if (value)
			return new ResponseEntity<>("Job updated Successfully", HttpStatus.OK);
		return new ResponseEntity<>("Job Not found", HttpStatus.NOT_FOUND);
	}
}
