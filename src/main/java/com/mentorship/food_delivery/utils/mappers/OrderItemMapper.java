package com.mentorship.food_delivery.utils.mappers;

import com.mentorship.food_delivery.dto.MenuItemDTO;
import com.mentorship.food_delivery.dto.OrderItemDTO;
import com.mentorship.food_delivery.model.order.OrderItem;
import java.util.Collection;

public class OrderItemMapper {
    public static OrderItemDTO toDto(OrderItem orderItem) {
        MenuItemDTO menuItemDTO = MenuItemMapper.toDto(orderItem.getMenuItem());

        return OrderItemDTO.builder()
                .quantity(orderItem.getQuantity())
                .menuItem(menuItemDTO)
                .build();
    }

    public static Iterable<OrderItemDTO> toDtos(Collection<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItemMapper::toDto)
                .toList();
    }
}
