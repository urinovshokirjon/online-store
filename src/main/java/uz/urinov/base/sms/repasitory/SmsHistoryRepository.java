package uz.urinov.base.sms.repasitory;



import org.springframework.data.jpa.repository.JpaRepository;
import uz.urinov.base.sms.entity.SmsHistoryEntity;

import java.time.LocalDateTime;
import java.util.Optional;


public interface SmsHistoryRepository extends JpaRepository<SmsHistoryEntity,Integer> {
    Optional<SmsHistoryEntity> findBySmsCodeAndPhone(String message, String phone);

    Optional<SmsHistoryEntity> findTopByPhoneOrderByCreateDateDesc(String phone);

    // countByEmailAndCreateDateBetween
    Long countByPhoneAndCreateDateBetween(String phone, LocalDateTime from, LocalDateTime to);
}
