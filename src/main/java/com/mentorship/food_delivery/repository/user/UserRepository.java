package com.mentorship.food_delivery.repository.user;


import com.mentorship.food_delivery.model.user.User;
import com.mentorship.food_delivery.configuration.security.UserDetailsRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends UserDetailsRepository<User, Integer> {

    Optional<User> findOptionalUserByUsername(String username);

    Optional<User> findOptionalUserByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);
}


