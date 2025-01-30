package com.mentorship.food_delivery.configuration.security;

import com.mentorship.food_delivery.configuration.security.filters.BasicAuthLoggingFilter;
import com.mentorship.food_delivery.configuration.security.filters.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService fetchUserDetailsByUsername(UserDetailsRepository<? extends UserDetails, ?> userRepository) {
        return userRepository::findByUsername;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity security,
            BasicAuthLoggingFilter basicAuthLoggingFilter,
            JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        return security.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(basicAuthLoggingFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/v1/customer/register",
                                        "/api/v1/admin/register",
                                        "/api/v1/restaurants/**",
                                        "api/v1/authentication/customer").permitAll()
                        .requestMatchers("/", "/**").authenticated()
                ).build();
    }
}
