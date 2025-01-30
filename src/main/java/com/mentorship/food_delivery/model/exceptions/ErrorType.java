package com.mentorship.food_delivery.model.exceptions;


import lombok.Getter;


@Getter
public enum ErrorType {
    GENERIC(1000),
    INVALID_FIELD(2000),
    DTO_VALIDATION_EXCEPTION(3000),
    RECOURSE_ALREADY_EXISTS(4000),
    RECOURSE_NOT_FOUND(4200),
    INVALID_USERNAME_OR_PASSWORD(5000),
    AUTHORIZATION(6000);

    final int code;

    ErrorType(int code) {
        this.code = code;
    }
}
