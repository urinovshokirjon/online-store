package uz.urinov.app.district.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.urinov.app.region.entity.RegionEntity;


import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "district")
public class DistrictEntity {

//    id, name_uz, name_en, name_ru,  region_id, created_date, visible, county

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "name_uz", length = 50, unique = true)
    private String nameUz;

    @Column(name = "name_en", length = 50, unique = true)
    private String nameEn;


    @Column(name = "name_ru", length = 50, unique = true)
    private String nameRu;

    @Column(name = "region_id")
    private Integer regionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id",insertable = false, updatable = false)
    private RegionEntity region;

    @Column(name = "created_date")
    private LocalDate createDate=LocalDate.now();

    @Column(name = "visible")
    private Boolean visible=Boolean.TRUE;

    @Column(name = "county", length = 100, unique = true)
    private String county;

}
