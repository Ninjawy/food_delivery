package com.mentorship.food_delivery.repository.order;

import com.mentorship.food_delivery.model.order.OrderTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTrackingRepository extends JpaRepository<OrderTracking, Integer> {
}
