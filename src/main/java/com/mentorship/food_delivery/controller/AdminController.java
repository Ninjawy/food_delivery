package com.mentorship.food_delivery.controller;


import com.mentorship.food_delivery.dto.CustomerDTO;
import com.mentorship.food_delivery.dto.RegisterUser;
import com.mentorship.food_delivery.dto.ResponseDTO;
import com.mentorship.food_delivery.dto.UserDTO;
import com.mentorship.food_delivery.service.CustomerService;
import com.mentorship.food_delivery.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final CustomerService customerService;

    /**
     * Registered user may be other admin or a restaurant owner
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseDTO<UserDTO> registerUser(@Valid @RequestBody RegisterUser request) {
        UserDTO user = userService.registerUser(request);
        return ResponseDTO.success(user);
    }

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO<Page<CustomerDTO>> customers(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size) {
        Page<CustomerDTO> customers = customerService.getCustomers(page, size);
        return ResponseDTO.success(customers);
    }

    @GetMapping("/profile")
    ResponseDTO<UserDTO> adminProfile(Principal user) {
        UserDTO userDTO = userService.findUserDto(user);
        return ResponseDTO.success(userDTO);
    }
}
