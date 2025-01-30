package com.mentorship.food_delivery.repository.order;

import com.mentorship.food_delivery.model.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
