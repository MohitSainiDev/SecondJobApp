package com.mohit.SecondJobApp.review;

import java.util.List;

public interface ReviewService {
	List<Review> findAll(Long companyId);

	boolean createReview(Long id, Review review);

	Review getReviewById(Long Cid, Long id);

	boolean deleteReviewById(Long cid, Long id);

	boolean updateReviewById(Long cid, Long id, Review review);
}
