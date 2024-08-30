package com.chung.jpapaging_demo.service;

import com.chung.jpapaging_demo.dto.CategoryDto;
import com.chung.jpapaging_demo.utils.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto categoryDto);
    CategoryDto update(CategoryDto categoryDto);
    void deleteById(String id);
    List<CategoryDto> findAll();
    CategoryDto findByid(String id);
    BaseResponse importCategoryData(MultipartFile file);
}
