package com.mentorship.food_delivery.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class MenuItemDTO {
    private String itemName;
    private BigDecimal price;
}