package uz.urinov.app.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uz.urinov.app.order.dto.OrderItemResponseDto;
import uz.urinov.app.order.dto.OrderResponse;
import uz.urinov.app.order.entity.OrderEntity;
import uz.urinov.app.order.entity.OrderItemEntity;
import uz.urinov.base.util.CustomPage;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {


    @Mapping(target = "productsCount", source = "productsCount")
    public abstract OrderResponse toDto(OrderEntity entity);


    @Mapping(target = "productName", expression = "java(entity.getProduct().getName())" )
    public abstract OrderItemResponseDto toItemDto(OrderItemEntity entity);


    public abstract List<OrderItemResponseDto> toItemDtoList(List<OrderItemEntity> entityList);


    @Mapping(target = "isFirst", source = "first")
    @Mapping(target = "isLast", source = "last")
    @Mapping(target = "pageNumber", expression = "java(page.getNumber() + 1)" )
    public abstract CustomPage<OrderResponse> toCustomPage(Page<OrderEntity> page);




}
