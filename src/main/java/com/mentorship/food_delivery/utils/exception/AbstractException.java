package com.mentorship.food_delivery.utils.exception;

/**
 * An abstract base class for exceptions that implements {@link ErrorProvider}.
 */
public abstract class AbstractException extends RuntimeException implements ErrorProvider {
    public AbstractException() {
    }

    public AbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractException(Throwable cause) {
        super(cause);
    }

    public AbstractException(String message) {
        super(message);
    }
}
