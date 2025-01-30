package com.mentorship.food_delivery.controller;

import com.mentorship.food_delivery.model.other.CartItem;
import com.mentorship.food_delivery.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestParam Integer userId,
            @RequestParam Integer menuItemId,
            @RequestParam Integer quantity,
            @RequestParam BigDecimal price) {
        CartItem cartItem = cartService.addItemToCart(userId, menuItemId, quantity, price);
        return ResponseEntity.ok(cartItem);
    }

    @GetMapping("/view")
    public ResponseEntity<List<CartItem>> viewCart(@RequestParam Integer userId) {
        return ResponseEntity.ok(cartService.viewCart(userId));
    }

    @PutMapping("/modify")
    public ResponseEntity<CartItem> modifyCartItem(
            @RequestParam Integer itemId,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.modifyCartItem(itemId, quantity));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeItemFromCart(@RequestParam Integer itemId) {
        cartService.removeItemFromCart(itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@RequestParam Integer userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}

