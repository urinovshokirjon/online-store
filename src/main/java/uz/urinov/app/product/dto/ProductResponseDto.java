package uz.urinov.app.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import uz.urinov.app.product.enums.ProductStatus;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseDto {

    private Long id;

    private String name;

    private Double price;

    private String brend;

    private String description;

    private Long categoryId;

    private ProductStatus status;

    private List<String> attachIds;

}
