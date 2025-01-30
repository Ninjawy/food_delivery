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
@Table(name = "payment_type_configurations")
public class PaymentTypeConfiguration {
    @Id
    @Column(name = "payment_type_config_id", nullable = false)
    private Integer id;

    @Column(name = "config_name", length = 100)
    private String configName;

}