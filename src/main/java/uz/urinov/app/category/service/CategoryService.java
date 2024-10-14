package uz.urinov.app.category.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import uz.urinov.app.category.dto.CategoryCreateDto;
import uz.urinov.app.category.dto.CategoryResponseDto;
import uz.urinov.app.category.dto.CategoryUpdateDto;
import uz.urinov.app.category.repository.CategoryRepository;
import uz.urinov.app.category.spec.CategoryFilter;
import uz.urinov.app.util.ApiResponse;
import uz.urinov.base.util.CustomPage;


public interface CategoryService {

    ApiResponse<CategoryResponseDto> create(CategoryCreateDto dto);

    ApiResponse<CategoryResponseDto> update(CategoryUpdateDto dto);

    ApiResponse<CategoryResponseDto> get(Long id);

    ApiResponse<String> delete(Long id);

    ApiResponse<CustomPage<CategoryResponseDto>> getAll(CategoryFilter filter);
}
