package com.mentorship.food_delivery.model.other;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transaction_details")
public class TransactionDetail {
    @Id
    @Column(name = "transaction_detail_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Column(name = "detail", length = Integer.MAX_VALUE)
    private String detail;

}