package com.chung.jpapaging_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private List<CartItem> cartItems = new ArrayList<CartItem>();
    private double totalPrice;

    public void addItem(Product product, int quantity) {
        CartItem cartItem = new CartItem(product, quantity);
        cartItems.add(cartItem);
        updatePrice();
    }

    public void removeItem(Long id) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(id));
        updatePrice();
    }

    public void updateQuantity(Long productId, int quantity) {
        for(CartItem cartItem : cartItems) {
            if(cartItem.getProduct().getId().equals(productId)) {
                cartItem.setQuantity(quantity);
                break;
            }
        }
        updatePrice();
    }

    public void updatePrice(){
        totalPrice = 0;
        for(CartItem cartItem : cartItems) {
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
    }
}
