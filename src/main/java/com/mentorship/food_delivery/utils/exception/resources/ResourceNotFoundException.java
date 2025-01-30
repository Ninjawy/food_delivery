package com.mentorship.food_delivery.utils.exception.resources;

import com.mentorship.food_delivery.utils.exception.AbstractException;
import com.mentorship.food_delivery.model.exceptions.ErrorType;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends AbstractException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.RECOURSE_NOT_FOUND;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
