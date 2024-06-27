package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.SleepDataDto;
import com.busanit.mentalCare.dto.StressDataDto;
import com.busanit.mentalCare.model.SleepData;
import com.busanit.mentalCare.model.StressData;
import com.busanit.mentalCare.repository.SleepDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 스프링 시큐리티의 클래스를 상속받지 않는 UserService
@Service
@RequiredArgsConstructor
public class SleepDataService {
    private final SleepDataRepository userSleepDataRepository;

    // 해당날짜 데이터 불러오기
    public SleepDataDto getSelectedDateSleepData(String userId, String stdDate) {
        SleepData sld = userSleepDataRepository.selectedDateSleepData(userId, stdDate);

        // 데이터가 없는 경우
        if (sld == null) {

        }

        return sld.toDto();
    }

}
