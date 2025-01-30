package com.mentorship.food_delivery.model.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_statuses")
public class OrderStatus {
    @Id
    @Column(name = "order_status_id", nullable = false)
    private Integer id;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

}