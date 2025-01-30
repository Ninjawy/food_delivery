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
@Table(name = "payment_integration_types")
public class PaymentIntegrationType {
    @Id
    @Column(name = "payment_integration_type_id", nullable = false)
    private Integer id;

    @Column(name = "type_name", nullable = false, length = 100)
    private String typeName;

}