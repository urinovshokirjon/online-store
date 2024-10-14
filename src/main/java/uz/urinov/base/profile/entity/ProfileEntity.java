package uz.urinov.base.profile.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import uz.urinov.base.config.audit_config.AuditEntity;
import uz.urinov.base.profile.enums.ProfileRole;
import uz.urinov.base.profile.enums.ProfileStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "profile")
public class ProfileEntity extends AuditEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String surname;

    @Column(length = 50, unique = true)
    private String phone;

    @Column(length = 50)
    private String password;

    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @Enumerated(EnumType.STRING)
    private ProfileRole role;

    @Column(name = "visible")
    private Boolean visible=Boolean.TRUE;


}
