package uz.urinov.app.region.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import uz.urinov.app.district.dto.DistrictResponseDTO;


import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionResponseDTO {

    private Integer id;

    private String nameUz;

    private String nameEn;

    private String nameRu;

    private String name;

    private List<DistrictResponseDTO> districts;

    private Boolean visible;

    private LocalDate createDate;
}
