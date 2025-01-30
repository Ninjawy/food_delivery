package com.mentorship.food_delivery.service;

import com.mentorship.food_delivery.model.other.Cart;
import com.mentorship.food_delivery.model.other.CartItem;
import com.mentorship.food_delivery.repository.cart.CartItemRepository;
import com.mentorship.food_delivery.repository.cart.CartRepository;
import com.mentorship.food_delivery.repository.menu.MenuItemRepository;
import com.mentorship.food_delivery.repository.user.UserRepository;
import com.mentorship.food_delivery.utils.exception.resources.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public CartItem addItemToCart(Integer userId, Integer menuItemId, Integer quantity, BigDecimal price) {
        // Find or create cart for the user
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(userRepository.findById(userId)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found")));
                    return cartRepository.save(newCart);
                });

        // Create a new CartItem
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setMenuItem(menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found")));
        cartItem.setQuantity(quantity);
        cartItem.setPrice(price);

        return cartItemRepository.save(cartItem);
    }

    @Transactional
    public List<CartItem> viewCart(Integer userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        return cartItemRepository.findByCartId(cart.getId());
    }

    @Transactional
    public CartItem modifyCartItem(Integer cartItemId, Integer newQuantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        cartItem.setQuantity(newQuantity);
        return cartItemRepository.save(cartItem);
    }

    @Transactional
    public void removeItemFromCart(Integer cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Transactional
    public void clearCart(Integer userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        cartItemRepository.deleteByCartId(cart.getId());
    }
}
