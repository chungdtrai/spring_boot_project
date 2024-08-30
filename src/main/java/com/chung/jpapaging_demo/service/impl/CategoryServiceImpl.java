package com.chung.jpapaging_demo.service.impl;

import com.chung.jpapaging_demo.dto.CategoryDto;
import com.chung.jpapaging_demo.entity.Category;
import com.chung.jpapaging_demo.repository.CategoryRepository;
import com.chung.jpapaging_demo.service.CategoryService;
import com.chung.jpapaging_demo.utils.BaseResponse;
import com.chung.jpapaging_demo.utils.FileFactory;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = categoryRepository.save(Category.builder()
                        .name(categoryDto.getName())
                        .description(categoryDto.getDescription())
                        .build());
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(categoryDto.getDescription())
                .build();
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        Category category = Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();
        var re = categoryRepository.save(category);
        return CategoryDto.builder()
                .id(re.getId())
                .name(re.getName())
                .description(re.getDescription())
                .build();
    }

    @Override
    public void deleteById(String id) {
            categoryRepository.deleteCategoryByStringId(id);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> list = categoryRepository.findAll();
        return list.stream().map(item ->{
            return CategoryDto.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .description(item.getDescription())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findByid(String id) {
        Category category = categoryRepository.findCategoryById(id);
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .products(category.getProducts())
                .build();
    }

    @Override
    public BaseResponse importCategoryData(MultipartFile file) {
        Workbook workbook = FileFactory.getWorkbookStream(file);
        return null;
    }
}