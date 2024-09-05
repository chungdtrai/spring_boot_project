package com.chung.jpapaging_demo.controller;

import com.chung.jpapaging_demo.entity.Cart;
import com.chung.jpapaging_demo.entity.Product;
import com.chung.jpapaging_demo.service.impl.CartServiceImpl;
import com.chung.jpapaging_demo.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartServiceImpl cartService;
    private final ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<Cart> getCart() {
        return new ResponseEntity<>(cartService.getCart(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addCart(@RequestParam(value = "id") Long productid
            ,@RequestParam(value="quantity") Integer quantity ) {
        return new ResponseEntity<>(cartService.addToCart(productid, quantity), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Cart> updateCart(@RequestParam(value = "id") Long productid
            ,@RequestParam(value="quantity") Integer quantity ){
        return new ResponseEntity<>(cartService.updateCart(productid, quantity), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Cart> deleteCart(@RequestParam(value = "id") Long productid) {
        return new ResponseEntity<>(cartService.removeFromCart(productid), HttpStatus.OK);
    }
}
