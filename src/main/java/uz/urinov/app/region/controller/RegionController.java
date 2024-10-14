package uz.urinov.app.region.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.app.region.dto.RegionCreateDTO;
import uz.urinov.app.region.dto.RegionResponseDTO;
import uz.urinov.app.region.service.RegionService;
import uz.urinov.base.auth.enums.Language;
import uz.urinov.base.util.Result;


import java.util.List;

@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    // 1. Region create (ADMIN)
    @PostMapping("/adm/create")
    public ResponseEntity<Result> createRegion(@Valid @RequestBody RegionCreateDTO regionDto,
                                               @RequestHeader(value = "Accept-Language") Language lang) {
        Result result = regionService.createRegion(regionDto, lang);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(result);
    }

    // 2. Region update (ADMIN)
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<Result> updateRegion(@Valid @RequestBody RegionCreateDTO regionDto,
                                               @RequestHeader(value = "Accept-Language") Language lang,
                                               @PathVariable("id") int id) {
        Result result = regionService.updateRegion(regionDto, id, lang);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(result);
    }

    // 3. Region list (ADMIN)
    @GetMapping("/adm/list")
    public ResponseEntity<List<RegionResponseDTO>> getRegionList() {
        List<RegionResponseDTO> regionDtoList = regionService.getRegionList();
        return ResponseEntity.status(HttpStatus.OK).body(regionDtoList);
    }

    // 4. Region delete (ADMIN)
    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<Result> deleteRegion(@PathVariable int id,
                                               @RequestHeader(value = "Accept-Language") Language lang) {
        Result result = regionService.deleteRegion(id, lang);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(result);
    }

    // 5. Region By Lang
    @GetMapping("/lang")
    public ResponseEntity<List<RegionResponseDTO>> getRegionByLang2(@RequestHeader(value = "Accept-Language") Language lang) {
        List<RegionResponseDTO> regionLangDtoList = regionService.getRegionByLang(lang);
        return ResponseEntity.status(HttpStatus.OK).body(regionLangDtoList);
    }


}
