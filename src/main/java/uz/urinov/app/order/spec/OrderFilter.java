package uz.urinov.app.order.spec;

import lombok.Getter;
import lombok.Setter;
import uz.urinov.app.order.enums.BookingStatus;
import uz.urinov.base.spec.BaseFilter;
import uz.urinov.base.util.SecurityUtil;

@Setter
@Getter
public class OrderFilter extends BaseFilter {

    private BookingStatus status;

    public OrderSpecification retrieveSpecification() {
        OrderSpecification spec = new OrderSpecification();

        spec.addCriteria("createdBy", ":", SecurityUtil.getProfileId());
        spec.addCriteria("status",":", status);

        return spec;
    }
}
