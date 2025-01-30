package com.mentorship.food_delivery.model.user;

import lombok.Getter;


@Getter
public enum RoleEnum {
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER");

    private final String roleName;

    RoleEnum(String roleName) {
        this.roleName = roleName;
    }

    public static String getRoleName(RoleEnum roleEnum) {
        return roleEnum.getRoleName();
    }
}
