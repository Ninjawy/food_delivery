package com.mentorship.food_delivery.service;
import com.mentorship.food_delivery.model.restaurant.Restaurant;
import com.mentorship.food_delivery.repository.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllEnabledRestaurants() {
        return restaurantRepository.findAllEnabled();
    }

    public List<Restaurant> getRestaurantsByCategory(Long categoryId) {
        return restaurantRepository.findByCategory(categoryId);
    }

    public List<Restaurant> searchRestaurantsByName(String name) {
        return restaurantRepository.searchByName(name);
    }

    public List<Restaurant> getTopRatedRestaurants() {
        return restaurantRepository.findTopRatedRestaurants();
    }

    public List<Restaurant> filterRestaurants(Long categoryId, Boolean isEnabled, Double minRating) {
        return restaurantRepository.filterRestaurants(categoryId, isEnabled, minRating);
    }

    public Restaurant updateRestaurant(Long id, Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        restaurant.setName(updatedRestaurant.getName());
        restaurant.setRating(updatedRestaurant.getRating());
        restaurant.setCategory(updatedRestaurant.getCategory());
        restaurant.setUpdatedAt(LocalDateTime.now());
        return restaurantRepository.save(restaurant);
    }

    public Restaurant toggleRestaurantStatus(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        restaurant.setEnabled(!restaurant.isEnabled());
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getRecommendations() {
        return restaurantRepository.getRecommendations();
    }
}