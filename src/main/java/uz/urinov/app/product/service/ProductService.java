package uz.urinov.app.product.service;

import uz.urinov.app.product.dto.ProductCreateDto;
import uz.urinov.app.product.dto.ProductResponseDto;
import uz.urinov.app.product.spec.ProductFilter;
import uz.urinov.app.util.ApiResponse;
import uz.urinov.base.util.CustomPage;

public interface ProductService {

    // Product create
    ApiResponse<ProductResponseDto> create(ProductCreateDto dto);

    // Product update
    ApiResponse<ProductResponseDto> update(Long id,ProductCreateDto dto);

    // Product delete
    ApiResponse<String> delete(Long id);

    // Product get by id
    ApiResponse<ProductResponseDto> getById(Long id);

    // Product get filter
    ApiResponse<CustomPage<ProductResponseDto>> getFilter(ProductFilter filter);
}
