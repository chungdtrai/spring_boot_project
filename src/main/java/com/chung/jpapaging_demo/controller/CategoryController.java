package com.chung.jpapaging_demo.controller;

import com.chung.jpapaging_demo.dto.CategoryDto;
import com.chung.jpapaging_demo.entity.Category;
import com.chung.jpapaging_demo.repository.CategoryRepository;
import com.chung.jpapaging_demo.service.CategoryService;
import com.chung.jpapaging_demo.service.impl.CategoryServiceImpl;
import com.chung.jpapaging_demo.utils.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> crate(@Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.create(categoryDto));
    }

    @PutMapping("/update")
    public ResponseEntity<CategoryDto> update(@Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.update(categoryDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> delete(@RequestParam(value = "id") String id) {
        categoryService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<CategoryDto> search(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(categoryService.findByid(id));
    }

    @PostMapping("/import")
    public ResponseEntity<BaseResponse> importExcelFile(@RequestParam(value = "file")MultipartFile file){
        BaseResponse baseResponse = categoryService.importCategoryData(file);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
