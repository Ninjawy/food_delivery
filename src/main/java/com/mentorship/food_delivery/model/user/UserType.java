package com.mentorship.food_delivery.model.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_types")
public class UserType {

    public UserType(String typeName) {
        this.typeName = typeName;
    }

    @Id
    @Column(name = "user_type_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name", nullable = false, length = 50)
    private String typeName;

    @OneToMany(mappedBy = "userType", cascade = CascadeType.ALL)
    private List<User> users;
}