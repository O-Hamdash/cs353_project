package com.api.gobooking.review;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class ReviewRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public boolean save(Review review) {
        boolean success = false;

        String reviewInsert = "INSERT INTO " +
                "review (reviewer_id, rating, review_title, review_body, booking_id, review_date, likes) " +
                "VALUES (:reviewer_id, :rating, :review_title, :review_body, :booking_id, :review_date, :likes)";

        Query reviewQuery = entityManager.createNativeQuery(reviewInsert);

        reviewQuery.setParameter("reviewer_id", review.getReviewer_id());
        reviewQuery.setParameter("rating", review.getRating());
        reviewQuery.setParameter("review_title", review.getReview_title());
        reviewQuery.setParameter("review_body", review.getReview_body());
        reviewQuery.setParameter("booking_id", review.getBooking_id());
        reviewQuery.setParameter("review_date", review.getReview_date());
        reviewQuery.setParameter("likes", review.getLikes());

        reviewQuery.executeUpdate();

        success = true;
        return success;
    }

    public List<Review> findAll(){
        String sql = "select * from review";
        Query query = entityManager.createNativeQuery(sql, Review.class);
        return query.getResultList();
    }

    public Optional<Review> findById(Integer id){
        String sql = "select * from review r where r.review_id = :review_id";
        Query query = entityManager.createNativeQuery(sql, Review.class);

        query.setParameter("review_id", id);

        List<Review> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    @Transactional
    public void updateReview(Review review){
        String reviewUpdate = "UPDATE review " +
                "SET reviewer_id = :reviewer_id, rating = :rating, review_title = :review_title, review_body = :review_body, booking_id = :booking_id, review_date = :review_date, likes = :likes " +
                "WHERE review_id = :review_id";
        Query updateReviewQuery = entityManager.createNativeQuery(reviewUpdate);

        updateReviewQuery.setParameter("reviewer_id", review.getReviewer_id());
        updateReviewQuery.setParameter("rating", review.getRating());
        updateReviewQuery.setParameter("review_title", review.getReview_title());
        updateReviewQuery.setParameter("review_body", review.getReview_body());
        updateReviewQuery.setParameter("booking_id", review.getBooking_id());
        updateReviewQuery.setParameter("review_date", review.getReview_date());
        updateReviewQuery.setParameter("likes", review.getLikes());
        updateReviewQuery.setParameter("review_id", review.getReview_id());

        updateReviewQuery.executeUpdate();
    }

    @Transactional
    public void updateLikes(Integer id, Integer likes){
        String sql = "UPDATE review " +
                "SET likes = :likes " +
                "WHERE review_id = :review_id";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("review_id", id);
        query.setParameter("likes", likes);

        query.executeUpdate();
    }

    @Transactional
    public void deleteById(Integer id) {
        String sql = "DELETE FROM review WHERE review_id = :review_id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("review_id", id);
        query.executeUpdate();
    }

    @Transactional
    public void deleteReview(Review review) {
        String sql = "DELETE FROM review WHERE review_id = :review_id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("review_id", review.getReview_id());
        query.executeUpdate();
    }

    public BigDecimal getReviewFromProperty(Integer propertyId) {
        String sql = "SELECT AVG(rating) AS average_rating " +
                "FROM review " +
                "WHERE booking_id IN ( " +
                "    SELECT booking_id " +
                "    FROM booking " +
                "    WHERE property_id = :property_id " +
                ");";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("property_id", propertyId);

        BigDecimal averageRating = null;
        try {
            averageRating = (BigDecimal) query.getSingleResult();
        } catch (NoResultException e) {
            throw new IllegalStateException("no reviews found for this property");
        }

        return averageRating;
    }

    // SortMode is 0 for sort by rating, 1 for sort by likes, and 2 for date
    public List<Review> getReviewsByProperty(Integer propertyId, Integer sortMode) {
        String sql = "select * from review where booking_id in (" +
                "select booking_id from booking where property_id = :property_id) " +
                "order by ";

        if (sortMode == 1){
            sql = sql + "likes desc";
        } else if (sortMode == 2){
            sql = sql + "review_date desc";
        } else {
            sql = sql + "rating desc";
        }

        Query query = entityManager.createNativeQuery(sql, Review.class);

        query.setParameter("property_id", propertyId);

        return query.getResultList();
    }
}
