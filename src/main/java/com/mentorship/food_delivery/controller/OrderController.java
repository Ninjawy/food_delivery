package com.mentorship.food_delivery.controller;

import com.mentorship.food_delivery.dto.OrderDTO;
import com.mentorship.food_delivery.dto.PageDTO;
import com.mentorship.food_delivery.dto.ResponseDTO;
import com.mentorship.food_delivery.model.order.OrderItem;
import com.mentorship.food_delivery.service.OrderService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/history")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<PageDTO<OrderDTO>> getCustomerOrderHistory(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size,
            Principal principal) {
        PageDTO<OrderDTO> response = orderService.getCustomerOrderHistory(principal, page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/place")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<OrderDTO> placeOrder(@RequestParam Integer userId) {
        OrderDTO order = orderService.placeOrder(userId);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}/items")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<Void> updateOrderItems(@PathVariable Integer orderId, @RequestBody List<OrderItem> newOrderItems) {
        orderService.updateOrderItems(orderId, newOrderItems);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{orderId}/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Integer orderId, @RequestParam String status) {
        orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{orderId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer orderId) {
        OrderDTO order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@RequestParam String status) {
        List<OrderDTO> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}/cancel")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<Void> cancelOrder(@PathVariable Integer orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }
}

