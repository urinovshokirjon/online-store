package uz.urinov.base.profile.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.urinov.base.profile.entity.ProfileEntity;

import java.util.Optional;


public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {

    // Resent Phone code
    @Transactional(readOnly=true)
    Optional<ProfileEntity> findByPhoneAndVisibleTrue(String phone);

////    @NotNull
////    @Transactional(readOnly = true)
////    @Override
//    Optional<ProfileEntity> findById(String id);

    // Phone number exist
//    @Transactional(readOnly=true)
    Optional<ProfileEntity> findByPhone(String phone);
    // Profile login
    @Query("SELECT p FROM ProfileEntity AS p WHERE p.phone=?1 AND p.password=?2 AND p.visible=true AND p.status='ACTIVE'")
    Optional<ProfileEntity> findByPhoneAndPasswordAndVisibleTrueAndStatusActive(String phone, String password);

}
