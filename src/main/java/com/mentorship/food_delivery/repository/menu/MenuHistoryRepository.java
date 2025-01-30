package com.mentorship.food_delivery.repository.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mentorship.food_delivery.model.menu.MenuHistory;
import java.util.*;

@Repository
public interface MenuHistoryRepository extends JpaRepository<MenuHistory, Long> {

    @Query("SELECT mh FROM MenuHistory mh WHERE mh.menu.id = :menuId")
    List<MenuHistory> findHistoryByMenu(@Param("menuId") Long menuId);


}
