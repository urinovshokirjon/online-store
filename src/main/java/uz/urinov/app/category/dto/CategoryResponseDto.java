package uz.urinov.app.category.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponseDto {

    private Long parentId;

    private Long id;

    private String name;

    private List<CategoryResponseDto> children;

}
