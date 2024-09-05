package com.chung.jpapaging_demo.controller;

import com.chung.jpapaging_demo.dto.ProductDto;
import com.chung.jpapaging_demo.entity.Product;
import com.chung.jpapaging_demo.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
    public ResponseEntity<List<ProductDto>> findAll(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/findAllWithPage")
    public ResponseEntity<Page<Product>> findAllWithPage(Pageable pageable) {
        return new ResponseEntity<>(productService.findAllPage(pageable), HttpStatus.OK);
    }
}
