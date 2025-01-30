package com.mentorship.food_delivery.repository.restaurant;


import com.mentorship.food_delivery.model.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // 1. Find all restaurants that are enabled
    @Query("SELECT r FROM Restaurant r WHERE r.isEnabled = true")
    List<Restaurant> findAllEnabled();

    // 2. Find restaurants by category
    @Query("SELECT r FROM Restaurant r WHERE r.category.id = :categoryId")
    List<Restaurant> findByCategory(@Param("categoryId") Long categoryId);

    // 3. Search restaurants by name (case-insensitive search)
    @Query("SELECT r FROM Restaurant r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Restaurant> searchByName(@Param("name") String name);

    // 4. Find top-rated restaurants (ordered by rating descending)
    @Query("SELECT r FROM Restaurant r WHERE r.isEnabled = true ORDER BY r.rating DESC")
    List<Restaurant> findTopRatedRestaurants();

    // 5. Native Query: Find restaurants based on a rating threshold
    @Query(value = "SELECT * FROM restaurant WHERE rating >= :ratingThreshold ORDER BY rating DESC", nativeQuery = true)
    List<Restaurant> findByRatingThreshold(@Param("ratingThreshold") double ratingThreshold);

    // 6. Advanced: Filter restaurants based on multiple parameters (e.g., category, enabled status, and rating)
    @Query("SELECT r FROM Restaurant r WHERE " +
            "(:categoryId IS NULL OR r.category.id = :categoryId) AND " +
            "(:isEnabled IS NULL OR r.isEnabled = :isEnabled) AND " +
            "(:minRating IS NULL OR r.rating >= :minRating)")
    List<Restaurant> filterRestaurants(
            @Param("categoryId") Long categoryId,
            @Param("isEnabled") Boolean isEnabled,
            @Param("minRating") Double minRating
    );



    @Query("SELECT r FROM Restaurant r WHERE r.rating >= 4.5 AND r.isEnabled = true ORDER BY RANDOM()")
    List<Restaurant> getRecommendations();
}