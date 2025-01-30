package com.mentorship.food_delivery.repository.customer;


import com.mentorship.food_delivery.model.customer.Customer;
import com.mentorship.food_delivery.model.user.User;
import jdk.jfr.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByUser(User user);

    Optional<Customer> findOptionalByUserUsername(String username);

    @Description("check using the user repository")
    boolean existsByUserUsernameOrUserEmailOrPhone(String username, String email, String phone);

    boolean existsByUserUsername(String username);

    boolean existsByUserUsernameAndUserPassword(String username, String password);

    @Description("check using the user repository")
    boolean existsByUserEmail(String email);

    boolean existsByPhone(String phone);

    Optional<Customer> findById(Integer customerId);
}