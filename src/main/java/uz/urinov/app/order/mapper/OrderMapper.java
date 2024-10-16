package uz.urinov.app.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import uz.urinov.app.Attach.repository.AttachRepository;
import uz.urinov.app.category.dto.CategoryResponseDto;
import uz.urinov.app.category.entity.CategoryEntity;
import uz.urinov.app.order.dto.OrderItemResponseDto;
import uz.urinov.app.order.dto.OrderResponse;
import uz.urinov.app.order.entity.OrderEntity;
import uz.urinov.app.order.entity.OrderItemEntity;
import uz.urinov.app.product.dto.ProductCreateDto;
import uz.urinov.app.product.dto.ProductResponseDto;
import uz.urinov.app.product.entity.ProductEntity;
import uz.urinov.base.util.CustomPage;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {


    public abstract OrderResponse toDto(OrderEntity entity);


    @Mapping(target = "productName", expression = "java(entity.getProduct().getName())" )
    public abstract OrderItemResponseDto toItemDto(OrderItemEntity entity);


    public abstract List<OrderItemResponseDto> toItemDtoList(List<OrderItemEntity> entityList);


    @Mapping(target = "isFirst", source = "first")
    @Mapping(target = "isLast", source = "last")
    @Mapping(target = "pageNumber", expression = "java(page.getNumber() + 1)" )
    public abstract CustomPage<OrderResponse> toCustomPage(Page<OrderEntity> page);




}
