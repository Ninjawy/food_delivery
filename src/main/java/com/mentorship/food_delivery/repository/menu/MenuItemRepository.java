package com.mentorship.food_delivery.repository.menu;


import com.mentorship.food_delivery.model.menu.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
}