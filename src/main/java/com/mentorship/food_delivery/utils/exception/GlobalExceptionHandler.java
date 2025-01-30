package com.mentorship.food_delivery.utils.exception;

import com.mentorship.food_delivery.dto.ErrorDTO;
import com.mentorship.food_delivery.dto.ResponseDTO;
import com.mentorship.food_delivery.model.exceptions.ErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDTO<Void> handleGenericException(Exception ex) {
        logErrorMessage(ex);
        return ResponseDTO.failure(ErrorDTO.create(new GenericException("Internal Server Error!")));
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseDTO<Void> handleGenericException(AuthorizationDeniedException ex) {
        logErrorMessage(ex);
        return ResponseDTO.failure(ErrorDTO.create("No sufficient authority", ErrorType.AUTHORIZATION));
    }

    @ExceptionHandler(AbstractException.class)
    public ResponseEntity<ResponseDTO<Void>> handleCustomException(AbstractException ex) {
        logErrorMessage(ex);
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ResponseDTO.failure(ErrorDTO.create(ex)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO<Void> handleValidationErrorException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining());
        logErrorMessage(message);
        return ResponseDTO.failure(ErrorDTO.create(message, ErrorType.DTO_VALIDATION_EXCEPTION));
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static void logErrorMessage(String message) {
        LOGGER.info("Handled Exception: {}", message);
    }

    private static void logErrorMessage(Throwable throwable) {
        logErrorMessage("%s -> %s".formatted(throwable.getClass().getSimpleName(), throwable.getMessage()));
    }
}