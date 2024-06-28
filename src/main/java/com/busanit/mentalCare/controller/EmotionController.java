package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.dto.EmotionDto;
import com.busanit.mentalCare.model.Emotion;
import com.busanit.mentalCare.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/emotion")
@RestController
public class EmotionController {
    @Autowired
    EmotionService emotionService;

    /* 메소드 */
    // 이모션 id로 정보 들고오기
    @GetMapping("/getEmotionById")
    public ResponseEntity<EmotionDto> getEmotionById(Long emotionId) {
        EmotionDto emotionDto = emotionService.getEmotionById(emotionId);
        return ResponseEntity.ok(emotionDto);
    }

    // 이모션 추가
    @PostMapping("/createEmotion")
    public ResponseEntity<String> createEmotion(@RequestBody EmotionDto emotionDto) {
        emotionService.saveEmotion(emotionDto);
        return ResponseEntity.ok("이모션 추가 완료");
    }

    // 이모션 삭제
    @PostMapping("/deleteEmotion")
    public ResponseEntity<String> deleteEmotion(@RequestBody Long emotionId) {
        int result = emotionService.deleteEmotion(emotionId);
        try {
            if (result == 1) {
                return ResponseEntity.ok("이모션 삭제 완료");
            } else {
                return ResponseEntity.ok("이모션 삭제 실패");
            }
        } catch (Exception e) {
            throw new RuntimeException("이모션 삭제 시 오류 발생");
        }
    }

    // 전체 이모션 조회
    @GetMapping("/AllEmotions")
    public ResponseEntity<List<EmotionDto>> listOfAllEmotions() {
        List<EmotionDto> allEmotionList = emotionService.listOfAllEmotions();
        return ResponseEntity.ok(allEmotionList);
    }
}