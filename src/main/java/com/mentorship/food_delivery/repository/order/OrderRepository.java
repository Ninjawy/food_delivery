package com.mentorship.food_delivery.repository.order;

import com.mentorship.food_delivery.model.customer.Customer;
import com.mentorship.food_delivery.model.order.Order;
import com.mentorship.food_delivery.model.order.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Page<Order> findByCustomer(Customer customer, Pageable pageable);

    List<Order> findByCustomer(Customer customer);

    List<Order> findByOrderStatus(OrderStatus orderStatus);
 }
