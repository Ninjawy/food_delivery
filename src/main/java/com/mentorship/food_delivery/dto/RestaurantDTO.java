package com.mentorship.food_delivery.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDTO {

    private String name;
    private UserDTO owner;
    private AddressDTO address;
}
