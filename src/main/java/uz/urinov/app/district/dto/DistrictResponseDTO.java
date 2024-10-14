package uz.urinov.app.district.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistrictResponseDTO {

    private Integer id;

    private Integer regionId;

    private String nameUz;

    private String nameEn;

    private String nameRu;

    private String name;

    private String country;

    private Boolean visible;

    private LocalDate createDate;
}
