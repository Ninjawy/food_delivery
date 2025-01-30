package com.mentorship.food_delivery.controller;


import com.mentorship.food_delivery.dto.CustomerDTO;
import com.mentorship.food_delivery.dto.LoginRequest;
import com.mentorship.food_delivery.dto.LoginResponse;
import com.mentorship.food_delivery.dto.ResponseDTO;
import com.mentorship.food_delivery.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/authentication")
public class Authentication {

    private final CustomerService customerService;

    @PostMapping("/customer")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    ResponseDTO<LoginResponse<CustomerDTO>> loginCustomer(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse<CustomerDTO> loginResponse = customerService.login(loginRequest);
        return ResponseDTO.success(loginResponse);
    }
}

