package uz.urinov.app.order.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import uz.urinov.app.order.enums.BookingStatus;
import uz.urinov.base.config.audit_config.AuditEntity;


import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<OrderItemEntity> orderItemList;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "products_count")
    private Integer productsCount;

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.CHECKING;

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;

}
