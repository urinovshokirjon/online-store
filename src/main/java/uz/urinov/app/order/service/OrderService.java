package uz.urinov.app.order.service;

import uz.urinov.app.Attach.util.ApiResponse;
import uz.urinov.app.order.dto.CreateOrderDto;
import uz.urinov.app.order.dto.OrderResponse;
import uz.urinov.app.order.spec.OrderFilter;
import uz.urinov.base.util.CustomPage;

public interface OrderService {


    ApiResponse<String> order(CreateOrderDto dto);

    ApiResponse<OrderResponse> getOrder(Long id);

    ApiResponse<CustomPage<OrderResponse>> getAll(OrderFilter filter);
}
