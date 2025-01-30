package com.mentorship.food_delivery.service;

import com.mentorship.food_delivery.model.other.Role;
import com.mentorship.food_delivery.model.user.RoleEnum;
import com.mentorship.food_delivery.model.user.UserType;
import com.mentorship.food_delivery.model.user.UserTypeEnum;
import com.mentorship.food_delivery.repository.user.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mentorship.food_delivery.model.user.RoleEnum.ADMIN;
import static com.mentorship.food_delivery.model.user.RoleEnum.CUSTOMER;

@Service
@RequiredArgsConstructor
public class RoleTypeService {
    private final RoleRepository roleRepository;

    public List<Role> findRoles(UserType userType) {
        List<String> roles = getUserRoles(userType);
        return roleRepository.findByRoleNameIn(roles);
    }


    public List<String> findRolesInString(RoleEnum... roleEnums) {
        List<String> roles = Arrays.stream(roleEnums)
                .map(roleEnum -> RoleEnum.getRoleName(roleEnum))
                .toList();

        return roleRepository.findByRoleNameIn(roles)
                .stream()
                .map(Role::getRoleName)
                .toList();
    }

    private List<String> getUserRoles(UserType userType) {
        if (checkUserType(userType, UserTypeEnum.CUSTOMER))
            return List.of(RoleEnum.getRoleName(CUSTOMER));
        else if (checkUserType(userType, UserTypeEnum.ADMIN))
            return List.of(RoleEnum.getRoleName(ADMIN));
        else
            return new ArrayList<>();
    }

    private static boolean checkUserType(UserType userType, UserTypeEnum userTypeEnum) {
        return userType.getTypeName().equals(userTypeEnum.getUserType());
    }
}
