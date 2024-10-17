package uz.urinov.app.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.urinov.app.Attach.util.ApiResponse;
import uz.urinov.app.category.entity.CategoryEntity;
import uz.urinov.app.category.spec.CategorySpecification;
import uz.urinov.app.order.mapper.OrderMapper;
import uz.urinov.app.order.spec.OrderFilter;
import uz.urinov.app.order.spec.OrderSpecification;
import uz.urinov.app.product.entity.ProductEntity;
import uz.urinov.app.product.service.ProductServiceImp;
import uz.urinov.app.order.dto.CreateOrderDto;
import uz.urinov.app.order.dto.OrderItemResponseDto;
import uz.urinov.app.order.dto.OrderResponse;
import uz.urinov.app.order.dto.ProductCount;
import uz.urinov.app.order.entity.OrderEntity;
import uz.urinov.app.order.entity.OrderItemEntity;
import uz.urinov.app.order.repository.OrderItemRepository;
import uz.urinov.app.order.repository.OrderRepository;
import uz.urinov.base.exp.AppBadException;
import uz.urinov.base.util.CustomPage;
import uz.urinov.base.util.SecurityUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductServiceImp productServiceImp;
    private final OrderMapper orderMapper;

    @Override
    public ApiResponse<String> order(CreateOrderDto dto) {
        
        OrderEntity orderEntity = new OrderEntity();
//        orderRepository.save(orderEntity);

        Double orderTotalPrice = 0.0;
        int pcount = 0;

        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        for (ProductCount productCount : dto.getProductCountList()) {
            ProductEntity productEntity = productServiceImp.getByIdProduct(productCount.getProductId());
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrder(orderEntity);
            orderItemEntity.setProductId(productEntity.getId());
            orderItemEntity.setProductCount(productCount.getProductsCount());

            double totalPrice = productEntity.getPrice() * productCount.getProductsCount();
            orderItemEntity.setTotalPrice(totalPrice);
            orderTotalPrice = orderTotalPrice + totalPrice;

            orderItemEntities.add(orderItemEntity);
            pcount += productCount.getProductsCount();
        }

        orderEntity.setTotalPrice(orderTotalPrice);// Umumiy narxni belgilang
        orderEntity.setProductsCount(pcount);
        orderEntity.setOrderItemList(orderItemEntities);
        orderRepository.save(orderEntity);
        return new ApiResponse<>("Food order ", 201, false);
    }

    @Override
    public ApiResponse<OrderResponse> getOrder(Long id) {
        OrderEntity entity = getOrderOwnerById(id);
        return new ApiResponse<>(200, false, orderMapper.toDto(entity));
    }

    @Override
    public ApiResponse<CustomPage<OrderResponse>> getAll(OrderFilter filter) {
        OrderSpecification spec = filter.retrieveSpecification();

        Page<OrderEntity> page = orderRepository.findAll(spec.getSpecification(), filter.pageable());

        return new ApiResponse<>(200,false,orderMapper.toCustomPage(page));
    }

    @Override
    public ApiResponse<String> deleteOrder(Long id) {
        OrderEntity entity = getOrderOwnerById(id);
        orderRepository.delete(entity);
        return new ApiResponse<>("Order delete "+id, 200, false);
    }

    public OrderEntity getOrderOwnerById(Long id) {
        return orderRepository.findByIdAndCreatedBy(id, SecurityUtil.getProfileId()).orElseThrow(() -> {
            throw new AppBadException("Not found product owner by id: " + id);
        });
    }

    @Transactional
    public void delete(Long id) {
        orderItemRepository.deleteAllByOrderId(id);
        orderRepository.deleteById(id);

    }


}
