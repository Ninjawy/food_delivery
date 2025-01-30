package com.mentorship.food_delivery.test_cases;


import com.mentorship.food_delivery.dto.CustomerDTO;

import java.util.Arrays;
import java.util.List;

public class CustomerDataset {

    public static List<CustomerDTO> customerListDto =
            Arrays.asList(
                    CustomerDTO.builder()
                            .phone("01155111844")
                            .email("m.tamer.fcai@gmail.com")
                            .userType("admin")
                            .username("mostafa")
                            .build(),
                    CustomerDTO.builder()
                            .phone("01155111833")
                            .email("a.tamer.fcai@gmail.com")
                            .userType("customer")
                            .username("abdelrahman")
                            .build(),
                    CustomerDTO.builder()
                            .phone("01143242423")
                            .email("ab.tamer.fcai@gmail.com")
                            .userType("restaurant_owner")
                            .username("mahmoud")
                            .build()
            );
}
