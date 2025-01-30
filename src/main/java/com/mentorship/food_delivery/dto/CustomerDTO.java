package com.mentorship.food_delivery.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    private String username;

    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Phone number must be a valid number and between 10 to 15 digits")
    private String phone;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "User Type is mandatory")
    private String userType;
}