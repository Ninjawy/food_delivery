package com.mentorship.food_delivery.model.user;

import lombok.Getter;

@Getter
public enum UserTypeEnum {
    CUSTOMER("customer"),
    ADMIN("admin"),
    RESTAURANT_OWNER("restaurant owner");

    private final String userType;

    UserTypeEnum(String userType) {
        this.userType = userType;
    }
}
