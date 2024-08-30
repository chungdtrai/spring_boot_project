package com.chung.jpapaging_demo.service.impl;

import com.chung.jpapaging_demo.dto.ProductDto;
import com.chung.jpapaging_demo.entity.Product;
import com.chung.jpapaging_demo.repository.ProductRepository;
import com.chung.jpapaging_demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = productRepository.save(Product.builder()
                        .name(productDto.getName())
                        .price(productDto.getPrice())
                        .quantity(productDto.getQuantity())
                        .category(productDto.getCategory())
                        .colors(productDto.getColors())
                .build());
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryName(product.getCategory().getName())
                .colors(product.getColors())
                .build();
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        Product product = Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .category(productDto.getCategory())
                .colors(productDto.getColors())
                .build();
        productRepository.save(product);
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryName(product.getCategory().getName())
                .colors(product.getColors())
                .build();
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto searchById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryName(product.getCategory().getName())
                .colors(product.getColors())
                .build();
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(item ->{
            return ProductDto.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .price(item.getPrice())
                    .quantity(item.getQuantity())
                    .category(item.getCategory())
                    .colors(item.getColors())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Page<Product> findAllPage(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products;
    }

}