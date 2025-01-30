package com.mentorship.food_delivery.utils.exception.resources;

import com.mentorship.food_delivery.utils.exception.AbstractException;
import com.mentorship.food_delivery.model.exceptions.ErrorType;

public class ResourceAlreadyExistsException extends AbstractException {

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.RECOURSE_ALREADY_EXISTS;
    }
}
