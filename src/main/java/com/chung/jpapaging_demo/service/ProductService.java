package com.chung.jpapaging_demo.service;

import com.chung.jpapaging_demo.dto.ProductDto;
import com.chung.jpapaging_demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ProductService {
    ProductDto create(ProductDto productDto);
    ProductDto update(ProductDto productDto);
    void delete(Long id);
    ProductDto searchById(Long id);
    List<ProductDto> findAll();
    Page<Product> findAllPage(Pageable pageable);
}
