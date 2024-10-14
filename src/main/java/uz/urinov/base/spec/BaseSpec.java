package uz.urinov.base.spec;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;


@NoArgsConstructor
public class BaseSpec<T> implements Specification<T> {

    protected SearchCriteria criteria;

    public BaseSpec(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria == null || criteria.getValue() == null) return null;

        if (criteria.getOperation().equalsIgnoreCase(">")) {

            return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());

        } else if (criteria.getOperation().equalsIgnoreCase("<")) {

            return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());

        } else if (criteria.getOperation().equalsIgnoreCase(":")) {

            if (root.get(criteria.getKey()).getJavaType() == String.class) {

                return builder.like(builder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%");

            } else {

                return builder.equal(root.get(criteria.getKey()), criteria.getValue());

            }

        } else if (criteria.getOperation().equalsIgnoreCase("=")) {

            return builder.equal(root.get(criteria.getKey()), criteria.getValue());

        }
        return null;
    }
}