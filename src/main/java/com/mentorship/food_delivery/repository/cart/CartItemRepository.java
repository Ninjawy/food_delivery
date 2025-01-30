package com.mentorship.food_delivery.repository.cart;

import com.mentorship.food_delivery.model.other.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCartId(Integer cartId);

    void deleteByCartId(Integer cartId);
}

