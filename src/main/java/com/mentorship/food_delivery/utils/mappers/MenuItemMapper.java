package com.mentorship.food_delivery.utils.mappers;

import com.mentorship.food_delivery.dto.MenuItemDTO;
import com.mentorship.food_delivery.model.menu.MenuItem;

public class MenuItemMapper {
    public static MenuItemDTO toDto(MenuItem menuItem){
        return MenuItemDTO.builder()
                .itemName(menuItem.getItemName())
                .price(menuItem.getPrice())
                .build();
    }
}
