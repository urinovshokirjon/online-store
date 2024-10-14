package uz.urinov.app.category.spec;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import uz.urinov.app.category.entity.CategoryEntity;
import uz.urinov.base.spec.BaseSpec;
import uz.urinov.base.spec.SearchCriteria;

public class CategorySpecification extends BaseSpec<CategoryEntity> {
    private SearchCriteria criteria;
    private Specification<CategoryEntity> specification = Specification.where(null);

    public CategorySpecification() {

    }
    public CategorySpecification( SearchCriteria criteria) {
        super(criteria);
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<CategoryEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria == null || criteria.getValue()==null) return null;

        return super.toPredicate(root, query, builder);

    }

    public void addCriteria(String key, String operation, Object value) {
        if (value != null){
            specification = specification.and(new CategorySpecification(new SearchCriteria(key, operation, value)));
        }
    }


    public Specification<CategoryEntity> getSpecification() { return specification;}

}
