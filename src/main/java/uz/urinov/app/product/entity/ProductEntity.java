package uz.urinov.app.product.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.urinov.app.Attach.entity.AttachEntity;
import uz.urinov.app.product.enums.ProductStatus;
import uz.urinov.app.category.entity.CategoryEntity;
import uz.urinov.base.config.audit_config.AuditEntity;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "brend")
    private String brend;

    @Column(name = "category_id")
    private Long categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @OneToMany
    private List<AttachEntity> attachEntityList;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;

}
