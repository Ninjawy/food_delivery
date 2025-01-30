package com.mentorship.food_delivery.configuration.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.core.userdetails.UserDetails;

@NoRepositoryBean
public interface UserDetailsRepository<T extends UserDetails, ID> extends JpaRepository<T, ID> {

    UserDetails findByUsername(String username);
}
