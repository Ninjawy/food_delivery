package com.mentorship.food_delivery.model.other;

import com.mentorship.food_delivery.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id", nullable = false)
    private Long id;

    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();
}