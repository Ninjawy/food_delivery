package com.mentorship.food_delivery.model.menu;

import com.mentorship.food_delivery.model.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "menu")
@Data // Lombok will generate getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok will generate a no-argument constructor
@AllArgsConstructor // Lombok will generate an all-arguments constructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private BigDecimal price;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private MenuCategory category;

    // No need to manually create getters and setters as Lombok handles it
    // Lombok also generates the toString(), equals(), and hashCode() methods

}