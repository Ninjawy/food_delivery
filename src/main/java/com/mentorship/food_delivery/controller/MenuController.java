package com.mentorship.food_delivery.controller;
import com.mentorship.food_delivery.model.menu.Menu;
import com.mentorship.food_delivery.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/v1/menus")
public class MenuController {
    private final MenuService menuService;

    // Constructor-based Dependency Injection
    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
    @PostMapping
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {

        return ResponseEntity.status(201).body(menuService.createMenu(menu));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Long id, @RequestBody Menu updatedMenu) {
        return ResponseEntity.ok(menuService.updateMenu(id, updatedMenu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }
}
