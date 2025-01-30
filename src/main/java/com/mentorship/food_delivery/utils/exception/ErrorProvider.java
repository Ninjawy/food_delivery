package com.mentorship.food_delivery.utils.exception;

import com.mentorship.food_delivery.model.exceptions.ErrorType;
import org.springframework.http.HttpStatus;

public interface ErrorProvider {

    ErrorType getErrorType();

    String getMessage();

    default HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
