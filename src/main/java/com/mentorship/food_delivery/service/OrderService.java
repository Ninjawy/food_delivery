package com.mentorship.food_delivery.service;

import com.mentorship.food_delivery.dto.OrderDTO;
import com.mentorship.food_delivery.dto.PageDTO;
import com.mentorship.food_delivery.model.order.Order;
import com.mentorship.food_delivery.model.order.OrderItem;
import com.mentorship.food_delivery.model.other.Cart;
import com.mentorship.food_delivery.model.other.CartItem;
import com.mentorship.food_delivery.repository.customer.CustomerRepository;
import com.mentorship.food_delivery.repository.order.OrderStatusRepository;
import com.mentorship.food_delivery.utils.exception.resources.ResourceNotFoundException;
import com.mentorship.food_delivery.utils.mappers.OrderMapper;
import com.mentorship.food_delivery.model.customer.Customer;
import com.mentorship.food_delivery.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final CartService cartService;
    private final OrderStatusRepository orderStatusRepository;
    private final CustomerRepository customerRepository;

    public PageDTO<OrderDTO> getCustomerOrderHistory(Principal user, int page, int size) {
        Customer customer = customerService.findCustomer(user);
        PageRequest pageable = PageRequest.of(page, size);
        Page<OrderDTO> orderPage = orderRepository.findByCustomer(customer, pageable)
                .map(OrderMapper::toDto);
        return PageDTO.create(orderPage);
    }


    @Transactional
    public OrderDTO placeOrder(Integer customerId) {
        try {
            List<CartItem> cartItems = cartService.viewCart(customerId);
            if (cartItems.isEmpty()) {
                throw new IllegalArgumentException("Cart is empty. Add items before placing an order.");
            }
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));

            Order order = createOrder(customer);

            List<OrderItem> orderItems = createOrderItems(order, cartItems);
            order.setOrderItems(orderItems);
            order.setTotalAmount(calculateTotalAmount(cartItems));

            Order savedOrder = orderRepository.save(order);
            cartService.clearCart(customerId);

            return OrderMapper.toDto(savedOrder);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Failed to place order: " + e.getMessage());
        }
    }

    private Order createOrder(Customer customer) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setCreatedAt(Instant.now());
        order.setOrderStatus(orderStatusRepository.findByStatus("PENDING"));
        return order;
    }

    private List<OrderItem> createOrderItems(Order order, List<CartItem> cartItems) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setMenuItem(cartItem.getMenuItem());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    private BigDecimal calculateTotalAmount(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void updateOrderStatus(Integer orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        order.setOrderStatus(orderStatusRepository.findByStatus(status));
        orderRepository.save(order);
    }


    public void updateOrderItems(Integer orderId, List<OrderItem> newOrderItems) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        order.setOrderItems(newOrderItems);
        orderRepository.save(order);
    }

    public OrderDTO getOrderById(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        return OrderMapper.toDto(order);
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getOrdersByStatus(String status) {
        List<Order> orders = orderRepository.findByOrderStatus(orderStatusRepository.findByStatus(status));
        return orders.stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    public void cancelOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        order.setOrderStatus(orderStatusRepository.findByStatus("CANCELLED"));
        orderRepository.save(order);
    }

//
//    public List<OrderDTO> getCustomerOrders(Integer customerId) {
//        Customer customer = customerRepository.findById(customerId).
//                orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));
//        List<Order> orders = orderRepository.findByCustomer(customer);
//        return orders.stream()
//                .map(OrderMapper::toDto)
//                .collect(Collectors.toList());
//    }

}
