package uz.urinov.app.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {

    @NotNull
    private List<ProductCount> productCountList;

}
