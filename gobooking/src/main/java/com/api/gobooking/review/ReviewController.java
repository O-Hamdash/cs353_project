package com.api.gobooking.review;

import com.api.gobooking.user.appuser.AppUser;
import com.api.gobooking.user.appuser.AppUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "gobooking/review")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public List<Review> getReviews(){
        return reviewService.getReviews();
    }

    @GetMapping(path = "{review_id}")
    public Review getReview(@PathVariable("review_id") Integer review_id){
        return reviewService.getReview(review_id);
    }

    @PostMapping
    public void addNewReview(@RequestBody ReviewRequest reviewRequest){
        reviewService.addReview(reviewRequest);
    }

    @DeleteMapping(path = "{review_id}")
    public void deleteReview(@PathVariable("review_id") Integer review_id){
        reviewService.deleteReview(review_id);
    }

    @PutMapping(path = "{review_id}")
    public void updateReview( @PathVariable("review_id") Integer review_id,
                               @RequestParam(required = false) Integer rating,
                               @RequestParam(required = false) String review_title,
                               @RequestParam(required = false) String review_body
    )
    {
        reviewService.updateReview(review_id, rating, review_title, review_body);
    }

    @PutMapping(path = "update_likes/{review_id}")
    public void updateLikes(@PathVariable("review_id") Integer review_id){
        reviewService.incrementLikes(review_id);
    }
}
