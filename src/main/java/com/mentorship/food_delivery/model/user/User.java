package com.mentorship.food_delivery.model.user;

import com.mentorship.food_delivery.dto.RegisterUser;
import com.mentorship.food_delivery.model.other.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails, Principal {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    @Override
    public String getName() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

    public static User create(RegisterUser registerCustomer,
                              String encryptedPassword,
                              UserType userType,
                              List<Role> roles) {
        return User.builder()
                .username(registerCustomer.getUsername())
                .password(encryptedPassword)
                .email(registerCustomer.getEmail())
                .userType(userType)
                .roles(roles)
                .createdAt(Instant.now())
                .build();
    }

    public static User of(String username, String password, String email, UserType userType) {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .userType(userType)
                .createdAt(Instant.now())
                .build();
    }
}

