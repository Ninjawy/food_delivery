package com.mentorship.food_delivery.utils.exception;

import com.mentorship.food_delivery.model.exceptions.ErrorType;
import org.springframework.http.HttpStatus;

/**
 * Represents a generic exception that extends {@link AbstractException}.
 * Used for cases where a specific exception type is not applicable.
 */
public class GenericException extends AbstractException {

    public GenericException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.GENERIC;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
