package com.mentorship.food_delivery.model.other;

import com.mentorship.food_delivery.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "auditing")
public class Auditing {
    @Id
    @Column(name = "audit_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "action")
    private String action;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "\"timestamp\"")
    private Instant timestamp;

}