package uz.urinov.app.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductCount {

    @NotNull(message = " ProductCount id bo'sh bo'lishi mumkin emas")
    @Min(value = 1, message = "ProductCount id  ning qiymati minimal 1 bo'lsin")
    private Long productId;

    @NotNull(message = " products count bo'sh bo'lishi mumkin emas")
    @Min(value = 1, message = "products count  ning qiymati minimal 1 bo'lsin")
    private Integer productsCount;
}
