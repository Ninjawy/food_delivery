package com.mentorship.food_delivery.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterCustomer extends RegisterUser {

    @NotNull
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Phone number must be a valid number and between 10 to 15 digits")
    private String phone;
}

