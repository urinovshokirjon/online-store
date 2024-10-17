package uz.urinov.app.category.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.urinov.base.config.audit_config.AuditEntity;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "name", length = 50, unique = true)
    private String name;

    @Column(name = "parent_id")
    private Long parentId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id",insertable = false, updatable = false)
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CategoryEntity> children;

}
