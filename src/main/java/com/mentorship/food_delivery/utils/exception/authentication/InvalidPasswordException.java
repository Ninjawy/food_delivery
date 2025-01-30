package com.mentorship.food_delivery.utils.exception.authentication;

import com.mentorship.food_delivery.utils.exception.AbstractException;
import com.mentorship.food_delivery.model.exceptions.ErrorType;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends AbstractException {

    public InvalidPasswordException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.INVALID_USERNAME_OR_PASSWORD;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
