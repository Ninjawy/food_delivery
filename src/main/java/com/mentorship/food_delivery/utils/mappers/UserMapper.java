package com.mentorship.food_delivery.utils.mappers;

import com.mentorship.food_delivery.dto.UserDTO;
import com.mentorship.food_delivery.model.user.User;


public class UserMapper {

    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .userType(user.getUserType().getTypeName())
                .username(user.getUsername())
                .build();
    }
}

