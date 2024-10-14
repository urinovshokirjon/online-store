package uz.urinov.app.product.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.app.product.dto.ProductCreateDto;
import uz.urinov.app.product.dto.ProductResponseDto;
import uz.urinov.app.product.service.ProductService;
import uz.urinov.app.product.spec.ProductFilter;
import uz.urinov.app.util.ApiResponse;
import uz.urinov.base.util.CustomPage;

import static uz.urinov.app.util.ApiUrls.PRODUCT_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT_URL)
public class ProductController {
    private final ProductService productService;

    // Product create
    @PostMapping()

    public ResponseEntity<ApiResponse<ProductResponseDto>> create(@Valid @RequestBody ProductCreateDto dto){
        return ResponseEntity.ok().body(productService.create(dto));
    }

    // Product update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> update(@PathVariable Long id, @RequestBody ProductCreateDto dto){
        return ResponseEntity.ok().body(productService.update(id,dto));
    }

    // Product delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id){
        return ResponseEntity.ok().body(productService.delete(id));
    }

    // Product get by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(productService.getById(id));
    }

    // Product get filter
    @GetMapping()
    public ResponseEntity<ApiResponse<CustomPage<ProductResponseDto>>> getFilter(@RequestBody ProductFilter filter){
        return ResponseEntity.ok().body(productService.getFilter(filter));
    }


}
