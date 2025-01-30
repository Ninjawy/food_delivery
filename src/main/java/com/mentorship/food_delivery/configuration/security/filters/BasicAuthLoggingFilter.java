package com.mentorship.food_delivery.configuration.security.filters;

import com.mentorship.food_delivery.configuration.security.AuthenticationFailureHandler;
import com.mentorship.food_delivery.model.security.HeaderType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class BasicAuthLoggingFilter extends OncePerRequestFilter implements AuthenticationFailureHandler {

    private final AuthenticationManager authenticationManager;

    private static final String USERNAME_AND_PASSWORD_AUTHENTICATION_REGEX = "^Basic [^:]+:[^:]+$";
    private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthLoggingFilter.class);

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        Optional<String> authHeader = getHeaderValue(request);
        authHeader.ifPresent(header -> {
            Authentication token = getUsernamePasswordAuthenticationToken(header);

            try {
                Authentication authentication = authenticationManager.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                LOGGER.info("Username {} authenticated", authentication.getName());
            } catch (AuthenticationException e) {
                String message = "Username %s failed to authenticate".formatted(token.getName());
                sendAuthenticationFailureResponse(message, response);
                LOGGER.error(message);
            }
        });

        filterChain.doFilter(request, response);
    }

    private static Optional<String> getHeaderValue(HttpServletRequest request) {
        String credentials = request.getHeader(HeaderType.AUTHORIZATION.getHeaderKey());

        if (credentials == null || !credentials.matches(USERNAME_AND_PASSWORD_AUTHENTICATION_REGEX)) {
            LOGGER.info("No valid basic authorization header found.");
            return Optional.empty();
        }

        return Optional.of(credentials);
    }

    private Authentication getUsernamePasswordAuthenticationToken(String header) {
        String credentials = header.substring("Basic ".length());
        String[] headerValue = credentials.split(":", 2);

        String username = headerValue[0];
        String password = headerValue[1];
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}