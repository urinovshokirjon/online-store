package uz.urinov.app.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import uz.urinov.app.product.enums.ProductStatus;

import java.util.List;

@Setter
@Getter
public class ProductCreateDto {

    @NotBlank(message = "Name bo'sh bo'lishi mumkin emas")
    @Size(min = 3, max = 50, message = "Berilgan (Name) ning uzunligi 3 va 50 orasida bo'lishi kerak")
    private String name;

    @NotNull(message = "price bo'sh bo'lishi mumkin emas")
    @Min(value = 1, message = "Prisening qiymati minimal 1 bo'lsin")
    private Double price;

    @NotBlank(message = "Brend bo'sh bo'lishi mumkin emas")
    @Size(min = 3, max = 50, message = "Berilgan brend ning uzunligi 3 va 50 orasida bo'lishi kerak")
    private String brend;

    @NotBlank(message = "Description bo'sh bo'lishi mumkin emas")
    @Size(min = 3, message = "Field haqida to'liqroq ma'lumot bo'lishi kerak")
    private String description;

    @NotNull(message = " category  id bo'sh bo'lishi mumkin emas")
    @Min(value = 1, message = "category  id  ning qiymati minimal 1 bo'lsin")
    private Long categoryId;

    @NotNull(message = " Status bo'sh bo'lishi mumkin emas")
    private ProductStatus status;

    @NotNull(message = "Product rasmi bo'lishi kerak")
    private List<String> attachIds;
}
