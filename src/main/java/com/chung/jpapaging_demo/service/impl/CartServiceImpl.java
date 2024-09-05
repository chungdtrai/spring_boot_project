package com.chung.jpapaging_demo.service.impl;

import com.chung.jpapaging_demo.entity.Cart;
import com.chung.jpapaging_demo.entity.Product;
import com.chung.jpapaging_demo.exception.InvalidQuantityException;
import com.chung.jpapaging_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl {

    private final Cart cart = new Cart();
    @Autowired
    private ProductServiceImpl productService;

    public Cart getCart() {
        return cart;
    }

    public Cart addToCart(Long productId, int quantity) {
        Product product = getProductById(productId);
        if(checkQuantity(product, quantity)) {
            cart.addItem(product, quantity);
        }
        return cart;
    }

    public Cart removeFromCart(Long productId) {
        cart.removeItem(productId);
        return cart;
    }

    public Cart updateCart(Long productId, int quantity) {
        Product product = getProductById(productId);
        if(checkQuantity(product, quantity)) {
            cart.updateQuantity(productId, quantity);
        }
        return cart;
    }

    private boolean checkQuantity(Product product, int quantity){
        if(product.getQuantity() < quantity){
            throw new InvalidQuantityException("Không đủ số lượng sản phẩm trong kho" +
                    ", hiện tại trong kho chỉ còn " + product.getQuantity() + " sản phẩm");
        }
        return true;
    }

    private Product getProductById(Long productId) {
        return productService.getProductById(productId);
    }
}
