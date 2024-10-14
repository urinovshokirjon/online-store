package uz.urinov.app.product.spec;

import lombok.Getter;
import lombok.Setter;
import uz.urinov.app.product.enums.ProductStatus;
import uz.urinov.base.spec.BaseFilter;

@Setter
@Getter
public class ProductFilter extends BaseFilter {

    private String name;
    private String price;
    private String brend;
    private Long categoryId;
    private Long parentId;
    private ProductStatus status;

    public ProductSpecification retrieveSpecification() {
        ProductSpecification spec = new ProductSpecification();

        spec.addCriteria("name", ":", name);
        spec.addCriteria("price", "<=", price);
        spec.addCriteria("brend", ":", brend);
        spec.addCriteria("categoryId", ":", categoryId);
        spec.addCriteria("parentId", ":", parentId);
        spec.addCriteria("status", ":", status);

        return spec;
    }
}
