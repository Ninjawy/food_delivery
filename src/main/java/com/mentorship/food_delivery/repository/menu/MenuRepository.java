package com.mentorship.food_delivery.repository.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mentorship.food_delivery.model.menu.Menu;
import java.util.*;
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id = :restaurantId")
    List<Menu> findMenusByRestaurant(@Param("restaurantId") Long restaurantId);

    @Query("SELECT m FROM Menu m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Menu> searchMenusByName(@Param("name") String name);

}
