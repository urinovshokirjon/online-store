package uz.urinov.app.product.spec;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import uz.urinov.app.product.entity.ProductEntity;
import uz.urinov.app.category.entity.CategoryEntity;
import uz.urinov.base.spec.BaseSpec;
import uz.urinov.base.spec.SearchCriteria;

public class ProductSpecification extends BaseSpec<ProductEntity> {

    private SearchCriteria criteria;
    private Specification<ProductEntity> specification = Specification.where(null);

    public ProductSpecification() {
    }

    public ProductSpecification(SearchCriteria criteria) {
        super(criteria);
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria == null || criteria.getValue() == null) return null;

        // categoryId bo'yicha filtr
        if (criteria.getKey().equals("categoryId")) {
            Join<CategoryEntity, ProductEntity> category = root.join("category");
            return builder.equal(category.get("id"), criteria.getValue());
        }

        // parentId bo'yicha filtr
        if (criteria.getKey().equals("parentId")) {
            Join<CategoryEntity, ProductEntity> category = root.join("category");
            Join<CategoryEntity, CategoryEntity> parentCategory = category.join("parent");
            return builder.equal(parentCategory.get("id"), criteria.getValue());
        }

        return super.toPredicate(root, query, builder);
    }

    public void addCriteria(String key, String operation, Object value) {
        if (value != null) {
            specification = specification.and(new ProductSpecification(new SearchCriteria(key, operation, value)));
        }
    }

    public Specification<ProductEntity> getSpecification() {
        return specification;
    }

}
