package uz.urinov.app.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {

    private Long id;

    private String customerId;

    private List<OrderItemResponseDto> orderItemList;

    private Double totalPrice;

    private Integer productCount;

    private Timestamp createdDate;
}
