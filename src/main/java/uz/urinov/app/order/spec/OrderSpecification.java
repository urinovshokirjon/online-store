package uz.urinov.app.order.spec;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import uz.urinov.app.category.entity.CategoryEntity;
import uz.urinov.app.order.entity.OrderEntity;
import uz.urinov.base.spec.BaseSpec;
import uz.urinov.base.spec.SearchCriteria;

public class OrderSpecification extends BaseSpec<OrderEntity> {
    private SearchCriteria criteria;
    private Specification<OrderEntity> specification = Specification.where(null);

    public OrderSpecification() {

    }
    public OrderSpecification( SearchCriteria criteria) {
        super(criteria);
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria == null || criteria.getValue()==null) return null;

        return super.toPredicate(root, query, builder);

    }

    public void addCriteria(String key, String operation, Object value) {
        if (value != null){
            specification = specification.and(new OrderSpecification(new SearchCriteria(key, operation, value)));
        }
    }


    public Specification<OrderEntity> getSpecification() { return specification;}

}
