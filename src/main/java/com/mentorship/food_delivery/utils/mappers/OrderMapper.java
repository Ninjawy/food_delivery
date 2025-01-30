package com.mentorship.food_delivery.utils.mappers;

import com.mentorship.food_delivery.dto.OrderDTO;
import com.mentorship.food_delivery.dto.OrderItemDTO;
import com.mentorship.food_delivery.model.order.Order;

public class OrderMapper {
    public static OrderDTO toDto(Order order) {
        Iterable<OrderItemDTO> orderItems = OrderItemMapper.toDtos(order.getOrderItems());

        return OrderDTO.builder()
                .totalAmount(order.getTotalAmount())
                .menuItems(orderItems)
                .status(order.getOrderStatus().getStatus())
                .build();
    }
}
