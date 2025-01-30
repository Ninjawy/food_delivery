package com.mentorship.food_delivery.model.restaurant;

import com.mentorship.food_delivery.model.menu.Menu;
import com.mentorship.food_delivery.model.restaurant.Review;
import com.mentorship.food_delivery.model.user.User;
import com.mentorship.food_delivery.model.other.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @Column(name = "restaurant_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private RestaurantCategory category;

    @Column(nullable = false)
    private String phone;

    @Column(name = "rating")
    private Double rating;  // Rating field (e.g., 1.0 to 5.0)

    @Column(name = "isEnabled", nullable = false)
    private boolean isEnabled = true;  // To enable/disable the restaurant

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menus;

    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews;

}

