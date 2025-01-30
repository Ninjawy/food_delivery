package com.mentorship.food_delivery.repository.user;


import com.mentorship.food_delivery.model.user.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

    Optional<UserType> findByTypeName(String userType);
}