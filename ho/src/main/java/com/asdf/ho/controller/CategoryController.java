package com.asdf.ho.controller;



import com.asdf.ho.dto.category.CategoryDto;
import com.asdf.ho.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    //카테고리 저장
    @PostMapping("/category")
    public void registCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.saveCategory(categoryDto.getCategoryname());
    }

    //카테고리 조회
    @GetMapping("/category/{categoryId}")
    public CategoryDto getCategories(@PathVariable Long categoryId) {
        return categoryService.findCategories(categoryId);
    }


}
