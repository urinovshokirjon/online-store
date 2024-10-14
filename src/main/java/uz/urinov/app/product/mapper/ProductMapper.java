package uz.urinov.app.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import uz.urinov.app.Attach.repository.AttachRepository;
import uz.urinov.app.product.dto.ProductCreateDto;
import uz.urinov.app.product.dto.ProductResponseDto;
import uz.urinov.app.product.entity.ProductEntity;
import uz.urinov.base.util.CustomPage;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {


    @Autowired
    protected AttachRepository attachRepository;

    @Mapping(target = "attachIds", expression = "java(  entity.getAttachEntityList().stream().map(item->item.getId()).toList() )")
    public abstract ProductResponseDto toDto(ProductEntity entity);


    @Mapping(target = "attachEntityList", expression = "java(attachRepository.findAllById(dto.getAttachIds()))")
    public abstract ProductEntity toEntity(ProductCreateDto dto);

    @Mapping(target = "isFirst", source = "first")
    @Mapping(target = "isLast", source = "last")
    @Mapping(target = "pageNumber", expression = "java(page.getNumber() + 1)")
    public abstract CustomPage<ProductResponseDto> toCustomPage(Page<ProductEntity> page);
}
