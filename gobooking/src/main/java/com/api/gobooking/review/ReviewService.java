package com.api.gobooking.review;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public boolean reviewExists(Integer id) {
        return reviewRepository.findById(id).isPresent();
    }

    public List<Review> getReviews(){
        return reviewRepository.findAll();
    }

    public Review getReview(Integer id){
        Optional<Review> optionalReview = reviewRepository.findById(id);

        if (optionalReview.isEmpty()){
            throw new IllegalStateException(String.format("getReview: Review with id (%s) does not exist", id));
        }

        return optionalReview.get();
    }

    public boolean addReview(ReviewRequest reviewRequest){
        boolean success = false;
        Integer reviewer_id = 1; // manual for now
        Integer booking_id = 10; // manual for now
        Review review = new Review(reviewRequest, reviewer_id, booking_id);
        reviewRepository.save(review);

        success = true;
        return success;
    }

    public boolean deleteReview(Integer id){
        boolean success = false;
        Optional<Review> optionalReview = reviewRepository.findById(id);

        if (optionalReview.isEmpty()){
            throw new IllegalStateException(String.format("deleteReview: Review with id (%s) does not exist", id));
        }

        reviewRepository.deleteById(id);

        success = true;
        return success;
    }

    public boolean updateReview(Integer id, Integer rating, String review_title, String review_body){
        boolean success = false;
        Optional<Review> optionalReview = reviewRepository.findById(id);

        if (optionalReview.isEmpty()){
            throw new IllegalStateException(String.format("updateReview: Review with id (%s) does not exist", id));
        }

        Review review = optionalReview.get();

        if (rating != null){
            review.setRating(rating);
        }
        if (review_title != null){
            review.setReview_title(review_title);
        }
        if (review_body != null){
            review.setReview_body(review_body);
        }
        review.setReview_date(Timestamp.from(Instant.now()));

        reviewRepository.updateReview(review);

        success = true;
        return success;
    }

    public boolean incrementLikes(Integer id){
        boolean success = false;
        Optional<Review> optionalReview = reviewRepository.findById(id);

        if (optionalReview.isEmpty()){
            throw new IllegalStateException(String.format("incrementLikes: Review with id (%s) does not exist", id));
        }

        Review review = optionalReview.get();

        reviewRepository.updateLikes(id, review.getLikes() + 1);

        success = true;
        return success;
    }
}