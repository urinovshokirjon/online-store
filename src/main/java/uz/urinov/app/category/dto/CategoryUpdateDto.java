package uz.urinov.app.category.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryUpdateDto {

    @NotNull(message = " Category id bo'sh bo'lishi mumkin emas")
    @Min(value = 1, message = "Category id ning qiymati minimal 1 bo'lsin")
    private Long id;

    @NotBlank(message = "Name  bo'sh bo'lishi mumkin emas")
    @Size(min = 3, max = 50, message = "Berilgan category (Name) ning uzunligi 3 va 50 orasida bo'lishi kerak")
    private String name;


    @Min(value = 1, message = "Parent id ning qiymati minimal 1 bo'lsin")
    private Long parentId;


}
