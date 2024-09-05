package com.chung.jpapaging_demo.service;

import com.chung.jpapaging_demo.entity.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getItems();
    void add(Long productId, Integer quantity);
    void remove(Long productId);
    void update(Long productId, Integer quantity);
    void clear();
    int getCount();
    double getTotalPrice();
}
