package uz.urinov.app.category.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.app.category.dto.CategoryCreateDto;
import uz.urinov.app.category.dto.CategoryResponseDto;
import uz.urinov.app.category.dto.CategoryUpdateDto;
import uz.urinov.app.category.service.CategoryService;
import uz.urinov.app.category.spec.CategoryFilter;
import uz.urinov.app.util.ApiResponse;
import uz.urinov.base.util.CustomPage;

import static uz.urinov.app.util.ApiUrls.CATEGORY_PAGE_URL;
import static uz.urinov.app.util.ApiUrls.CATEGORY_URL;


@SecurityRequirement(name = "Authorization")
@RequiredArgsConstructor
@RestController
@RequestMapping(CATEGORY_URL)
public class CategoryController {
    private final CategoryService categoryService;

    // Category create
    @PostMapping()
    public ResponseEntity<ApiResponse<CategoryResponseDto>> create(@RequestBody CategoryCreateDto dto){
        return ResponseEntity.ok().body(categoryService.create(dto));
    }

    // Category update
    @PutMapping()
    public ResponseEntity<ApiResponse<CategoryResponseDto>> update(@RequestBody CategoryUpdateDto dto){
        return ResponseEntity.ok().body(categoryService.update(dto));
    }

    // Category delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id){
        return ResponseEntity.ok().body(categoryService.delete(id));
    }

    // Category get by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> getId(@PathVariable Long id){
        return ResponseEntity.ok().body(categoryService.getId(id));
    }

    // Category filter list
    @GetMapping(CATEGORY_PAGE_URL)
    public ResponseEntity<ApiResponse<CustomPage<CategoryResponseDto>>> getList(@RequestBody CategoryFilter filter){
        return ResponseEntity.ok().body(categoryService.getList(filter));
    }



}
