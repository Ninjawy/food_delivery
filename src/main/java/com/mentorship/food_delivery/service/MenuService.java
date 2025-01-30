package com.mentorship.food_delivery.service;
import com.mentorship.food_delivery.model.menu.Menu;
import com.mentorship.food_delivery.model.menu.MenuHistory;
import com.mentorship.food_delivery.repository.menu.MenuHistoryRepository;
import com.mentorship.food_delivery.repository.menu.MenuRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuHistoryRepository menuHistoryRepository;

    public Menu createMenu(Menu menu) {
        menu.setCreatedAt(LocalDateTime.now());
        menu.setUpdatedAt(LocalDateTime.now());
        return menuRepository.save(menu);
    }

    public Menu updateMenu(Long id, Menu updatedMenu) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        // Save history before updating
        MenuHistory menuHistory = new MenuHistory();
        menuHistory.setMenu(menu);
        menuHistory.setName(menu.getName());
        menuHistory.setDescription(menu.getDescription());
        menuHistory.setPrice(menu.getPrice());
        menuHistory.setUpdatedAt(menu.getUpdatedAt());
        menuHistoryRepository.save(menuHistory);

        // Update menu
        menu.setName(updatedMenu.getName());
        menu.setDescription(updatedMenu.getDescription());
        menu.setPrice(updatedMenu.getPrice());
        menu.setUpdatedAt(LocalDateTime.now());
        return menuRepository.save(menu);
    }

    public void deleteMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        menuRepository.delete(menu);
    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public List<MenuHistory> getMenuHistory(Long menuId) {
        return menuHistoryRepository.findHistoryByMenu(menuId);
    }
}
