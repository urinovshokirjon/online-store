package uz.urinov.app.category.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.urinov.app.category.dto.CategoryCreateDto;
import uz.urinov.app.category.dto.CategoryResponseDto;
import uz.urinov.app.category.dto.CategoryUpdateDto;
import uz.urinov.app.category.entity.CategoryEntity;
import uz.urinov.app.category.mapper.CategoryMapper;
import uz.urinov.app.category.repository.CategoryRepository;
import uz.urinov.app.category.spec.CategoryFilter;
import uz.urinov.app.category.spec.CategorySpecification;
import uz.urinov.app.util.ApiResponse;
import uz.urinov.base.exp.AppBadException;
import uz.urinov.base.util.CustomPage;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    // Category create
    @Override
    public ApiResponse<CategoryResponseDto> create(CategoryCreateDto dto) {
        getName(dto.getName());
        if (dto.getParentId() != null) {
            getById(dto.getParentId());
        }
        CategoryEntity entity = categoryMapper.toEntity(dto);
        entity = categoryRepository.save(entity);
        return new ApiResponse<>(201, false, categoryMapper.toDto(entity));
    }

    // Category update
    @Override
    public ApiResponse<CategoryResponseDto> update(CategoryUpdateDto dto) {
        getById(dto.getId());
        getById(dto.getParentId());
        CategoryEntity entity = categoryMapper.toEntity(dto);
        return new ApiResponse<>(200, false, categoryMapper.toDto(entity));
    }

    // Category delete
    @Override
    public ApiResponse<String> delete(Long id) {
        CategoryEntity category = getById(id);
        categoryRepository.delete(category);
        return new ApiResponse<>(200, false, "Deleted");
    }

    @Override
    public ApiResponse<CustomPage<CategoryResponseDto>> getAll(CategoryFilter filter) {
        CategorySpecification spec = filter.retrieveSpecification();

        Page<CategoryEntity> page = categoryRepository.findAll(spec.getSpecification(), filter.pageable());

        return new ApiResponse<>(200,false,categoryMapper.toCustomPage(page));
    }

    // Category get by id
    @Override
    public ApiResponse<CategoryResponseDto> get(Long id) {
        CategoryEntity category = getById(id);
        return new ApiResponse<>(200, false, categoryMapper.toDto(category));
    }




    public CategoryEntity getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new AppBadException("Error: category not found");
        });
    }

    public void getName(String name) {
        Optional<CategoryEntity> entityOptional = categoryRepository.findByNameIgnoreCase(name);
        if (entityOptional.isPresent()) {
            throw new AppBadException("Category already exists");
        }
    }

    ;


}
