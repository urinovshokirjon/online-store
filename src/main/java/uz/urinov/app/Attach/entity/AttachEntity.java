package uz.urinov.app.Attach.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.urinov.base.config.audit_config.AuditEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "attachs")
public class AttachEntity extends AuditEntity {
    @Id
    private String id;

    @Column(name = "path")
    private String path;  // 2024/09/22 papkali yo'li

    @Column(name = "extension")
    private String extension;  // jpg, png, mp3 ...

    @Column(name = "origen_name")
    private String origenName;  // ramsjon

    @Column(name = "size")
    private Long size;   // filening hajmi

    @Column(name = "compressed_attach_id")
    private String compressedAttachId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="compressed_attach_id", insertable = false, updatable = false )
    private AttachEntity compressedAttach;


}
