package uz.urinov.app.category.spec;

import lombok.Getter;
import lombok.Setter;
import uz.urinov.base.profile.enums.ProfileStatus;
import uz.urinov.base.spec.BaseFilter;
@Setter
@Getter
public class CategoryFilter extends BaseFilter {

    private String name;
    private Long parentId;

    public CategorySpecification retrieveSpecification() {
        CategorySpecification spec = new CategorySpecification();

        spec.addCriteria("name", ":", name);
        spec.addCriteria("parentId",":", parentId);

        return spec;
    }
}
