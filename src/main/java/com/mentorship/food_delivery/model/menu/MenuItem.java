package com.mentorship.food_delivery.model.menu;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @Column(name = "menu_item_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "item_name", length = 100)
    private String itemName;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}

