package uz.urinov.app.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.urinov.app.Attach.repository.AttachRepository;
import uz.urinov.app.product.dto.ProductCreateDto;
import uz.urinov.app.product.dto.ProductResponseDto;
import uz.urinov.app.product.entity.ProductEntity;
import uz.urinov.app.product.mapper.ProductMapper;
import uz.urinov.app.product.repository.ProductRepository;
import uz.urinov.app.product.spec.ProductFilter;
import uz.urinov.app.product.spec.ProductSpecification;
import uz.urinov.app.category.service.CategoryServiceImpl;
import uz.urinov.app.util.ApiResponse;
import uz.urinov.base.exp.AppBadException;
import uz.urinov.base.util.CustomPage;
import uz.urinov.base.util.SecurityUtil;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImp implements ProductService {

    private final CategoryServiceImpl categoryService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final AttachRepository attachRepository;

    // Product create
    @Override
    public ApiResponse<ProductResponseDto> create(ProductCreateDto dto) {
        categoryService.getById(dto.getCategoryId());

        ProductEntity entity = productMapper.toEntity(dto);

        return new ApiResponse<>(201, false, productMapper.toDto(productRepository.save(entity)));
    }

    // Product update
    @Override
    public ApiResponse<ProductResponseDto> update(Long id, ProductCreateDto dto) {
        getProductOwnerById(id);
        categoryService.getById(dto.getCategoryId());
        ProductEntity entity = productMapper.toEntity(dto);
        return new ApiResponse<>(200, false, productMapper.toDto(productRepository.save(entity)));
    }

    // Product delete
    @Override
    public ApiResponse<String> delete(Long id) {
        ProductEntity entity = getProductOwnerById(id);
        productRepository.delete(entity);
        return new ApiResponse<>(200, false, "Product deleted.");
    }

    // Product get by id
    @Override
    public ApiResponse<ProductResponseDto> getById(Long id) {
        ProductEntity entity = getByIdProduct(id);
        return new ApiResponse<>(200, false, productMapper.toDto(entity));
    }

    // Product get filter
    @Override
    public ApiResponse<CustomPage<ProductResponseDto>> getFilter(ProductFilter filter) {
        ProductSpecification spec = filter.retrieveSpecification();
        Page<ProductEntity> page = productRepository.findAll(spec.getSpecification(), filter.pageable());
        return new ApiResponse<>(200, false, productMapper.toCustomPage(page));
    }

    public ProductEntity getProductOwnerById(Long id) {
        return productRepository.findByIdAndCreatedBy(id, SecurityUtil.getProfileId()).orElseThrow(() -> {
            throw new AppBadException("Not found product owner by id: " + id);
        });
    }

    public ProductEntity getByIdProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> {
            throw new AppBadException("Not found product owner by id: " + id);
        });
    }

}
