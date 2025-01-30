package com.mentorship.food_delivery.dto;


public record ResponseDTO<T>(T data, ErrorDTO error, boolean success) {

    public ResponseDTO {
        if (success && error != null) {
            throw new IllegalArgumentException("A successful response cannot have an error.");
        }
        if (!success && data != null) {
            throw new IllegalArgumentException("A failure response cannot have data.");
        }
    }

    public static <R> ResponseDTO<R> success(R data) {
        return new ResponseDTO<>(data, null, true);
    }

    public static ResponseDTO<Void> failure(ErrorDTO errorDTO) {
        return new ResponseDTO<>(null, errorDTO, false);
    }
}
