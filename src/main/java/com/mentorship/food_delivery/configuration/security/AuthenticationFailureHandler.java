package com.mentorship.food_delivery.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentorship.food_delivery.dto.ErrorDTO;
import com.mentorship.food_delivery.dto.ResponseDTO;
import com.mentorship.food_delivery.model.exceptions.ErrorType;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.io.IOException;

public interface AuthenticationFailureHandler {

    default void sendAuthenticationFailureResponse(
            String errorMessage,
            @NonNull HttpServletResponse response) {

        try {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            ErrorDTO errorDetails = ErrorDTO.create(errorMessage, ErrorType.AUTHORIZATION);
            ResponseDTO<Void> failureResponse = ResponseDTO.failure(errorDetails);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(failureResponse);

            response.setContentLength(jsonResponse.length());
            response.getOutputStream().write(jsonResponse.getBytes());
//            response.getWriter().close();
        } catch (IOException ex) {
            Logger LOGGER = LoggerFactory.getLogger(AuthenticationFailureHandler.class);
            LOGGER.error("Exception occurred when handling un authorized response");
        }
    }
}