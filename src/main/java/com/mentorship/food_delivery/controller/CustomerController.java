package com.mentorship.food_delivery.controller;

import com.mentorship.food_delivery.dto.CustomerDTO;
import com.mentorship.food_delivery.dto.RegisterCustomer;
import com.mentorship.food_delivery.dto.ResponseDTO;
import com.mentorship.food_delivery.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public final class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseDTO<CustomerDTO> registerCustomer(@Valid @RequestBody RegisterCustomer request) {
        CustomerDTO customer = customerService.registerCustomer(request);
        return ResponseDTO.success(customer);
    }

    @GetMapping("/profile")
    ResponseDTO<CustomerDTO> customerProfile(Principal user) {
        CustomerDTO userDTO = customerService.findCustomerDTO(user);
        return ResponseDTO.success(userDTO);
    }
}
