package com.mentorship.food_delivery.model.other;

import com.mentorship.food_delivery.model.customer.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "preferred_payment_settings")
public class PreferredPaymentSetting {
    @Id
    @Column(name = "payment_setting_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_integration_type_id")
    private PaymentIntegrationType paymentIntegrationType;

}