package com.mentorship.food_delivery.configuration.security.filters;

import com.mentorship.food_delivery.model.security.AuthHeaderPrefix;
import com.mentorship.food_delivery.model.security.HeaderType;
import com.mentorship.food_delivery.service.security.JwtService;
import com.mentorship.food_delivery.configuration.security.AuthenticationFailureHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter implements AuthenticationFailureHandler {

    private final JwtService jwtService;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        Optional<String> optionalToken = getTokenFromRequest(request);
        optionalToken.ifPresent(token -> {
            try {
                String username = jwtService.extractUsername(token);

                List<? extends GrantedAuthority> authorities = jwtService.extractRoles(token);

                if (username != null) {
                    Authentication authentication =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                String message = "Error validating token: %s.".formatted(e.getMessage());
                sendAuthenticationFailureResponse(message, response);
                logger.error(message);
            }
        });

        filterChain.doFilter(request, response);
    }

    private Optional<String> getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HeaderType.AUTHORIZATION.getHeaderKey());
        String prefix = AuthHeaderPrefix.BEARER.getPrefix();

        boolean validAuthorizationValue = bearerToken != null && bearerToken.startsWith(prefix);

        if (validAuthorizationValue) {
            return Optional.of(bearerToken.substring(prefix.length()));
        } else {
            LOGGER.info("No valid JWT authorization header value found.");
            return Optional.empty();
        }
    }
}
