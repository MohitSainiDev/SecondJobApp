package com.mohit.SecondJobApp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.SecondJobApp.company.Company;
import com.mohit.SecondJobApp.company.CompanyService;
import com.mohit.SecondJobApp.review.Review;
import com.mohit.SecondJobApp.review.ReviewRepository;
import com.mohit.SecondJobApp.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	CompanyService companyService;

	@Override
	public List<Review> findAll(Long companyId) {
		return reviewRepository.findByCompanyId(companyId);
	}

	@Override
	public boolean createReview(Long id, Review review) {

		Company company = companyService.getCompanyById(id);

		if (company != null) {
			review.setCompany(company);
			reviewRepository.save(review);
			return true;
		}
		return false;
	}

	@Override
	public Review getReviewById(Long Cid, Long id) {
		List<Review> reviews = reviewRepository.findByCompanyId(Cid);
		for (Review r : reviews) {
			if (r.getId() == id)
				return r;
		}
		return null;
	}

	@Override
	public boolean deleteReviewById(Long cid, Long id) {
		List<Review> reviews = reviewRepository.findByCompanyId(cid);
		for (Review r : reviews) {
			if (r.getId() == id) {
				reviewRepository.deleteById(id);
				return true;
			}
		}
		return false;

	}

	@Override
	public boolean updateReviewById(Long cid, Long id, Review updatedReview) {

		List<Review> reviews = reviewRepository.findByCompanyId(cid);
		for (Review r : reviews) {
			if (r.getId() == id) {
				r.setTitle(updatedReview.getTitle());
				r.setDescriptiion(updatedReview.getDescriptiion());
				r.setRating(updatedReview.getRating());
				// r.setCompany(updatedReview.getCompany());
				reviewRepository.save(r);
				return true;
			}
		}
		return false;
	}


}
