package com.mentorship.food_delivery.model.customer;

import com.mentorship.food_delivery.dto.RegisterCustomer;
import com.mentorship.food_delivery.model.other.Role;
import com.mentorship.food_delivery.model.order.Order;
import com.mentorship.food_delivery.model.user.User;
import com.mentorship.food_delivery.model.user.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "customer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "phone", length = 15)
    private String phone;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public static Customer create(RegisterCustomer registerCustomer, User newUser) {
        return Customer.builder()
                .user(newUser)
                .phone(registerCustomer.getPhone())
                .build();
    }

    public static Customer create(RegisterCustomer registerCustomer, String password, UserType userType, List<Role> roles) {
        User user = User.create(registerCustomer, password, userType, roles);
        return create(registerCustomer, user);
    }
}

