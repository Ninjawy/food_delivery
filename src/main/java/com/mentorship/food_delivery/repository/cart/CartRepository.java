package com.mentorship.food_delivery.repository.cart;

import com.mentorship.food_delivery.model.other.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUserId(Integer userId);
}
