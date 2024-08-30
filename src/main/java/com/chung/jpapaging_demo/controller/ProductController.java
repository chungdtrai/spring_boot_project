package com.chung.jpapaging_demo.controller;

import com.chung.jpapaging_demo.dto.ProductDto;
import com.chung.jpapaging_demo.entity.Product;
import com.chung.jpapaging_demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.create(productDto));
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.update(productDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> delete(@RequestParam(value = "id")Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ProductDto> search(@RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(productService.searchById(id));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<ProductDto>> findAll() {
            return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/findAllWithPage")
    public ResponseEntity<Page<Product>> findAllWithPage(Pageable pageable) {
        return new ResponseEntity<>(productService.findAllPage(pageable), HttpStatus.OK);
    }
}
