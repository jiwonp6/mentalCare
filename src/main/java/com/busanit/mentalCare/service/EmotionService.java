package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.EmotionDto;
import com.busanit.mentalCare.model.Emotion;
import com.busanit.mentalCare.repository.EmotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmotionService {

    private final EmotionRepository emotionRepository;

    public EmotionDto getEmotionById(Long emotionId) {
        return emotionRepository.findById(emotionId).orElse(null).toDto();
    }

    // 이모션 추가
    public EmotionDto saveEmotion(EmotionDto emotionDto) {
        return emotionRepository.save(emotionDto.toEntity()).toDto();
    }

    // 이모션 삭제
    public int deleteEmotion(Long emotionId) {
        int result = emotionRepository.deleteEmotion(emotionId);
        return result;
    }

    // 전체 이모션 조회
    public List<EmotionDto> listOfAllEmotions() {
        List<Emotion> emotions = emotionRepository.findAll();
        return emotions.stream().map(Emotion::toDto).toList();
    }

}
