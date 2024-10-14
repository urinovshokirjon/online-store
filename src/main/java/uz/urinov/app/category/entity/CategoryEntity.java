package uz.urinov.app.category.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.urinov.base.config.audit_config.AuditEntity;

import static jakarta.persistence.GenerationType.IDENTITY;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name_uz", length = 50, unique = true)
    private String name;

    @Column(name = "parent_id")
    private Long parentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id",insertable = false, updatable = false)
    private CategoryEntity parent;

}
