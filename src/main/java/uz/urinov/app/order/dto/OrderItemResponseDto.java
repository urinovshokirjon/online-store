package uz.urinov.app.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemResponseDto {

    private Long id;
    private Long productId;
    private String productName;
    private Double totalPrice;
    private Integer productCount;
    private Long orderId;
    private Timestamp createdDate;
}
