package com.mentorship.food_delivery.model.other;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment_statuses")
public class PaymentStatus {
    @Id
    @Column(name = "payment_status_id", nullable = false)
    private Integer id;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

}