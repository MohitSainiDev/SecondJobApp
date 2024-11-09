package com.mohit.SecondJobApp.review;

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
@RequestMapping("/companies/{companyId}")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> findAll(@PathVariable Long companyId) {
		return ResponseEntity.ok(reviewService.findAll(companyId));

	}

	@PostMapping
	public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {

		boolean isReviewSaved = reviewService.createReview(companyId, review);
		if(isReviewSaved)
		return new ResponseEntity<>("Review created Successfully", HttpStatus.CREATED);
	return new ResponseEntity<>("Company does not exist", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/reviews/{id}")
	public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long id) {

		Review review = reviewService.getReviewById(companyId, id);
		if (review != null)
			return new ResponseEntity<>(review, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/reviews/{id}")
	public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long id) {

		boolean value = reviewService.deleteReviewById(companyId, id);
		if (value)
			return new ResponseEntity<>("Review deleted Successfully", HttpStatus.OK);
		return new ResponseEntity<>("Review Not found", HttpStatus.NOT_FOUND);
	}

	// @RequestMapping(value ="/jobs/{id}" ,method = RequestMethod.PUT )
	@PutMapping("/reviews/{id}")
	public ResponseEntity<String> updateReviewById(@PathVariable Long companyId, @PathVariable Long id,
			@RequestBody Review review) {

		boolean value = reviewService.updateReviewById(companyId, id, review);
		if (value)
			return new ResponseEntity<>("Review updated Successfully", HttpStatus.OK);
		return new ResponseEntity<>("Review Not found", HttpStatus.NOT_FOUND);
	}
}
