package com.mentorship.food_delivery.utils.mappers;

import com.mentorship.food_delivery.dto.CustomerDTO;
import com.mentorship.food_delivery.model.user.User;
import com.mentorship.food_delivery.model.user.UserType;
import com.mentorship.food_delivery.model.customer.Customer;
public class CustomerMapper {
    public static CustomerDTO toDto(Customer customer) {
        return CustomerDTO.builder()
                .email(customer.getUser().getEmail())
                .userType(customer.getUser().getUserType().getTypeName())
                .username(customer.getUser().getUsername())
                .phone(customer.getPhone())
                .build();
    }

    public static Customer toEntity(CustomerDTO customer, UserType userType) {
        User user = User.builder()
                .userType(userType)
                .username(customer.getUsername())
                .email(customer.getEmail())
                .build();

        return Customer.builder()
                .user(user)
                .phone(customer.getPhone())
                .build();
    }
}

