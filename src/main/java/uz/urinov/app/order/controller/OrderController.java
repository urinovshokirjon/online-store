package uz.urinov.app.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.app.Attach.util.ApiResponse;
import uz.urinov.app.category.dto.CategoryResponseDto;
import uz.urinov.app.category.spec.CategoryFilter;
import uz.urinov.app.order.dto.CreateOrderDto;
import uz.urinov.app.order.dto.OrderResponse;
import uz.urinov.app.order.service.OrderService;
import uz.urinov.app.order.spec.OrderFilter;
import uz.urinov.base.util.CustomPage;

import static uz.urinov.app.util.ApiUrls.CATEGORY_PAGE_URL;
import static uz.urinov.app.util.ApiUrls.ORDER_URL;


@SecurityRequirement(name = "Authorization")
@RequiredArgsConstructor
@RestController
@Tag(name = "Order controller", description = "API list for Order")
@RequestMapping(ORDER_URL)
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    @Operation(summary = "Create order", description = "Api for create order")
    public ResponseEntity<ApiResponse<String>> order(@RequestBody CreateOrderDto dto) {
        return ResponseEntity.ok(orderService.order(dto));
    }


    @GetMapping("/{id}")
    @Operation(summary = "Order by id", description = "Api for order by id")
    public ResponseEntity<ApiResponse<OrderResponse>> getFoodOrder(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    // Order filter list
    @GetMapping()
    public ResponseEntity<ApiResponse<CustomPage<OrderResponse>>> getAll(@RequestBody OrderFilter filter){
        return ResponseEntity.ok().body(orderService.getAll(filter));
    }



}
