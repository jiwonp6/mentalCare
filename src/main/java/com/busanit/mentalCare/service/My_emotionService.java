package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.My_emotionDto;
import com.busanit.mentalCare.model.My_emotion;
import com.busanit.mentalCare.repository.My_emotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

// 스프링 시큐리티의 클래스를 상속받지 않는 UserService
@Service
@RequiredArgsConstructor
public class My_emotionService {

    private final My_emotionRepository my_emotionRepository;

    // 내 이모션 추가
    public int createMyEmotion(My_emotion my_emotion) {
        int result = my_emotionRepository.createMyEmotion(my_emotion);
        return result;
    }

    // 내 이모션 수정
    public int updateMyEmotion(int my_emotion_id, My_emotion updateMyEmotion) {
        My_emotion my_emotion = my_emotionRepository.findByMyEmotionId(my_emotion_id);

        if (my_emotion != null) {
            if (true) {
                my_emotion.setEmotion_id(updateMyEmotion.getEmotion_id());
            }
            if (updateMyEmotion.getMy_emotion_reason() != null) {
                my_emotion.setMy_emotion_reason(updateMyEmotion.getMy_emotion_reason());
            }
            if (updateMyEmotion.getMy_emotion_date() != null) {
                my_emotion.setMy_emotion_date(updateMyEmotion.getMy_emotion_date());
            }
        }

        int result = my_emotionRepository.updateMyEmotion(my_emotion);
        return result;
    }

    // 내 이모션 삭제
    public int deleteMyEmotion(int my_emotion_id) {
        int result = my_emotionRepository.deleteMyEmotion(my_emotion_id);
        return result;
    }

    // 날짜별 내 이모션 리스트
    public List<My_emotionDto> listOfMyEmotionListByDate(Date my_emotion_date) {
        List<My_emotion> myEmotions = my_emotionRepository.listOfMyEmotionListByDate(my_emotion_date);
        return myEmotions.stream().map(My_emotion::toDto).toList();
    }

}
