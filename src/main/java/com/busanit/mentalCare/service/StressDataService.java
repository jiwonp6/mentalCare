package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.StressDataDto;
import com.busanit.mentalCare.model.StressData;
import com.busanit.mentalCare.repository.StressDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 스프링 시큐리티의 클래스를 상속받지 않는 UserService
@Service
@RequiredArgsConstructor
public class StressDataService {
    private final StressDataRepository userStressDataRepository;

    // 해당날짜 데이터 불러오기
    public StressDataDto getSelectedDateStressData(String userId, String stdDate) {
        StressData std = userStressDataRepository.selectedDateStressData(userId, stdDate);

        // 데이터가 없는 경우
        if (std == null) {

        }

        return std.toDto();
    }

}
