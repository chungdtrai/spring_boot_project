package com.chung.jpapaging_demo.dto;

import com.chung.jpapaging_demo.entity.Category;
import com.chung.jpapaging_demo.entity.Color;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    @NotBlank(message="tên không hợp lệ")
    private String name;
    @Min(value = 2, message = "số lượng không hợp lên, tối thiểu là 2")
    @Max(value = 50, message = "vượt quá số lượng cho phép, tối đa là 50")
    private Integer quantity;
    @NotNull(message = "giá không được để trống")
    private Double price;
    @NotNull(message = "Danh mục sản phẩm không được bỏ trống")
    private Category category;
    private String categoryName;
    @NotNull(message = "Chưa chọn màu sắc cho sản phẩm")
    private Set<Color> colors;
}
