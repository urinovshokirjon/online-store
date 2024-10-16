package uz.urinov.app.product.spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import uz.urinov.app.product.entity.ProductEntity;
import uz.urinov.app.category.entity.CategoryEntity;
import uz.urinov.base.spec.BaseSpec;
import uz.urinov.base.spec.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification extends BaseSpec<ProductEntity> {

    private SearchCriteria criteria;
    private Specification<ProductEntity> specification = Specification.where(null);

    public ProductSpecification() {
    }

    public ProductSpecification(SearchCriteria criteria) {
        super(criteria);
        this.criteria = criteria;
    }

    public List<Long> findAllChildCategoryIds(Long parentId) {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        List<Long> categoryIds = new ArrayList<>();

        try {
            Query nativeQuery = entityManager.createNativeQuery("SELECT c.id FROM categories as c WHERE c.parent_id = :parentId");
            nativeQuery.setParameter("parentId", parentId);
            List<Long> childIdList = nativeQuery.getResultList();
            categoryIds.add(parentId);

            for (Long childId : childIdList) {
                categoryIds.addAll(findAllChildCategoryIds(childId));
            }

        } finally {
            entityManager.close();
        }

        return categoryIds;
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
            List<Long> allChildCategoryIds = findAllChildCategoryIds((Long) criteria.getValue());
//            Join<CategoryEntity, ProductEntity> category = root.join("category");
//            Join<CategoryEntity, CategoryEntity> parentCategory = category.join("parent");
            return root.get("categoryId").in(allChildCategoryIds);
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
