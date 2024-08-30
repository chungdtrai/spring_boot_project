package com.chung.jpapaging_demo.dto;

import com.chung.jpapaging_demo.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private String id;
    @NotBlank(message = "Tên danh mục sản phẩm không được bỏ trống")
    private String name;
    private String description;
    @JsonIgnore
    private Collection<Product> products;
}
