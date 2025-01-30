package com.mentorship.food_delivery.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderDTO {

    private BigDecimal totalAmount;
    private Iterable<OrderItemDTO> menuItems;
    private String status;
}
