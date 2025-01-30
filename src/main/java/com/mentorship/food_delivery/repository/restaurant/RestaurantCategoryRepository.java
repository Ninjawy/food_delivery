package com.mentorship.food_delivery.repository.restaurant;


import com.mentorship.food_delivery.model.restaurant.RestaurantCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Long> {
}
