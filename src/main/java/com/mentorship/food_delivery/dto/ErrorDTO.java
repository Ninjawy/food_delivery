package com.mentorship.food_delivery.dto;

import com.mentorship.food_delivery.utils.exception.ErrorProvider;
import com.mentorship.food_delivery.model.exceptions.ErrorType;
import lombok.Builder;

@Builder
public record ErrorDTO(String errorType, int errorCode, String message) {

    public static ErrorDTO create(ErrorProvider provider) {
        return ErrorDTO.builder()
                .message(provider.getMessage())
                .errorType(provider.getErrorType().name())
                .errorCode(provider.getErrorType().getCode())
                .build();
    }

    public static ErrorDTO create(String message, ErrorType errorType) {
        return ErrorDTO.builder()
                .message(message)
                .errorType(errorType.name())
                .errorCode(errorType.getCode())
                .build();
    }
}
