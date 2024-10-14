package uz.urinov.app.category.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uz.urinov.app.category.dto.CategoryCreateDto;
import uz.urinov.app.category.dto.CategoryResponseDto;
import uz.urinov.app.category.dto.CategoryUpdateDto;
import uz.urinov.app.category.entity.CategoryEntity;
import uz.urinov.base.util.CustomPage;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDto toDto(CategoryEntity category);

    CategoryEntity toEntity(CategoryCreateDto dto);

    CategoryEntity toEntity(CategoryUpdateDto dto);

    List<CategoryResponseDto> toDtos(List<CategoryEntity> categories);

    @Mapping(target = "isFirst", source = "first")
    @Mapping(target = "isLast", source = "last")
    @Mapping(target = "pageNumber", expression = "java(page.getNumber() + 1)" )
    CustomPage<CategoryResponseDto> toCustomPage(Page<CategoryEntity> page);




}
