package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.MyEmotionDto;
import com.busanit.mentalCare.model.MyEmotion;
import com.busanit.mentalCare.repository.MyEmotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

// 스프링 시큐리티의 클래스를 상속받지 않는 UserService
@Service
@RequiredArgsConstructor
public class MyEmotionService {

    private final MyEmotionRepository my_emotionRepository;

    // 내 이모션 추가
    public int createMyEmotion(MyEmotionDto myEmotionDto) {
        int result = my_emotionRepository.createMyEmotion(myEmotionDto.toEntity());
        return result;
    }

    // 내 이모션 수정
    public int updateMyEmotion(Long myEmotionId, MyEmotionDto updateMyEmotionDto) {
        MyEmotion myEmotion = my_emotionRepository.findByMyEmotionId(myEmotionId);

        if (myEmotion != null) {
            if (true) {
                myEmotion.setEmotionId(updateMyEmotionDto.getEmotionId());
            }
            if (updateMyEmotionDto.getMyEmotionReason() != null) {
                myEmotion.setMyEmotionReason(updateMyEmotionDto.getMyEmotionReason());
            }
            if (updateMyEmotionDto.getMyEmotionDate() != null) {
                myEmotion.setMyEmotionDate(updateMyEmotionDto.getMyEmotionDate());
            }
        }

        int result = my_emotionRepository.updateMyEmotion(myEmotion);
        return result;
    }

    // 내 이모션 삭제
    public int deleteMyEmotion(Long myEmotionId) {
        int result = my_emotionRepository.deleteMyEmotion(myEmotionId);
        return result;
    }

    // 날짜별 내 이모션 리스트
    public List<MyEmotionDto> listOfMyEmotionListByDate(Date myEmotionDate) {
        List<MyEmotion> myEmotions = my_emotionRepository.listOfMyEmotionListByDate(myEmotionDate);
        return myEmotions.stream().map(MyEmotion::toDto).toList();
    }

}
