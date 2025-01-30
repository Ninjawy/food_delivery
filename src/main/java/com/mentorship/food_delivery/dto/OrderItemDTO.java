package com.mentorship.food_delivery.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OrderItemDTO {
    private Integer quantity;
    private MenuItemDTO menuItem;
}