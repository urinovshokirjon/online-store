package uz.urinov.app.region.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uz.urinov.app.region.entity.RegionEntity;
import uz.urinov.app.region.mapper.RegionMapper;


import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {

    // 3. Region list
//    @Transactional(readOnly = true)
    @Query("SELECT r FROM RegionEntity r where r.visible = true order by r.id")
    List<RegionEntity> findAllVisible();

    // 5. Region By Lang
//    @Transactional(readOnly = true)
    @Query(value = "SELECT id, " +
            " CASE :lang " +
            " WHEN 'UZ' THEN name_uz " +
            " WHEN 'RU' THEN name_ru " +
            " WHEN 'EN' THEN name_en " +
            " END AS name " +
            " FROM region ORDER BY order_number DESC; ", nativeQuery = true)
    List<RegionMapper> findAll(@Param("lang") String lang);


    @NotNull
    Optional<RegionEntity> findById(Integer id);

}
