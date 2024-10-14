package uz.urinov.base.sms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.urinov.base.exp.AppBadException;
import uz.urinov.base.sms.entity.SmsHistoryEntity;
import uz.urinov.base.sms.repasitory.SmsHistoryRepository;


import java.time.LocalDateTime;
@RequiredArgsConstructor
@Service
public class SmsHistoryService {

    private final SmsHistoryRepository smsHistoryRepository;

    public void createSmsHistory(String smsCode, String phone) {
        SmsHistoryEntity smsHistoryEntity = new SmsHistoryEntity();
        smsHistoryEntity.setSmsCode(smsCode);
        smsHistoryEntity.setPhone(phone);
        smsHistoryEntity.setCreateDate(LocalDateTime.now());
        smsHistoryRepository.save(smsHistoryEntity);

    }

    public void checkEmailLimit(String phone) {

        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minusMinutes(2);

        long count = smsHistoryRepository.countByPhoneAndCreateDateBetween(phone, from, to);
        if (count >= 3) {
            throw new AppBadException("Sms limit reached. Please try after some time");
        }
    }

}
