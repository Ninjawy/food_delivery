package com.mentorship.food_delivery.controller;


import com.mentorship.food_delivery.model.restaurant.Restaurant;
import com.mentorship.food_delivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        return ResponseEntity.status(201).body(restaurantService.addRestaurant(restaurant));
    }

    @GetMapping("/enabled")
    public ResponseEntity<List<Restaurant>> getAllEnabledRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllEnabledRestaurants());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Restaurant>> getRestaurantsByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantsByCategory(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurantsByName(@RequestParam String name) {
        return ResponseEntity.ok(restaurantService.searchRestaurantsByName(name));
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<Restaurant>> getTopRatedRestaurants() {
        return ResponseEntity.ok(restaurantService.getTopRatedRestaurants());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Restaurant>> filterRestaurants(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Boolean isEnabled,
            @RequestParam(required = false) Double minRating
    ) {
        return ResponseEntity.ok(restaurantService.filterRestaurants(categoryId, isEnabled, minRating));
    }

    // Update an existing restaurant
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(id, updatedRestaurant));
    }

    // Enable or Disable a restaurant
    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<Restaurant> toggleRestaurantStatus(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.toggleRestaurantStatus(id));
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<Restaurant>> getRecommendations() {
        return ResponseEntity.ok(restaurantService.getRecommendations());
    }
}