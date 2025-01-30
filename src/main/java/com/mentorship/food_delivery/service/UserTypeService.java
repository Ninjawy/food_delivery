package com.mentorship.food_delivery.service;

import com.mentorship.food_delivery.utils.exception.resources.ResourceNotFoundException;
import com.mentorship.food_delivery.model.user.UserType;
import com.mentorship.food_delivery.repository.user.UserTypeRepository;
import com.mentorship.food_delivery.model.user.UserTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTypeService {
    private final UserTypeRepository userTypeRepository;

    public UserType findByName(String name) {
        return userTypeRepository.findByTypeName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid user type"));
    }

    public UserType getCustomerUserType() {
        return userTypeRepository.findByTypeName(UserTypeEnum.CUSTOMER.getUserType())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid user type"));
    }
}
