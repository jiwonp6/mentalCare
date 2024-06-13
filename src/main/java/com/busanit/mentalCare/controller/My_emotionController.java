package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.dto.EmotionDto;
import com.busanit.mentalCare.dto.My_emotionDto;
import com.busanit.mentalCare.model.Emotion;
import com.busanit.mentalCare.model.My_emotion;
import com.busanit.mentalCare.service.EmotionService;
import com.busanit.mentalCare.service.My_emotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class My_emotionController {
    @Autowired
    My_emotionService my_emotionService;

    
    /* 메소드 */
    // 내 이모션 추가
    @PostMapping("/createMyEmotion")
    public ResponseEntity<String> createMyEmotion(@RequestBody My_emotion my_emotion) {
        my_emotionService.createMyEmotion(my_emotion);
        return ResponseEntity.ok("나의 이모션 추가 완료");
    }

    // 내 이모션 수정
    @GetMapping("/updateMyEmotion")
    public ResponseEntity<String> updateMyEmotion(@RequestBody int my_emotion_id, @RequestBody My_emotion my_emotion) {
        my_emotionService.updateMyEmotion(my_emotion_id, my_emotion);
        return ResponseEntity.ok("나의 이모션 수정 완료");
    }

    // 내 이모션 삭제
    @PostMapping("/deleteMyEmotion")
    public ResponseEntity<String> deleteMyEmotion(@RequestBody int my_emotion_id) {
        int result = my_emotionService.deleteMyEmotion(my_emotion_id);
        try {
            if (result == 1) {
                return ResponseEntity.ok("나의 이모션 삭제 완료");
            } else {
                return ResponseEntity.ok("나의 이모션 삭제 실패");
            }
        } catch (Exception e) {
            throw new RuntimeException("나의 이모션 삭제 시 오류 발생");
        }
    }

    // 날짜별 내 이모션 리스트
    @GetMapping("/listOfMyEmotionListByDate")
    public ResponseEntity<List<My_emotionDto>> listOfMyEmotionListByDate(@RequestBody Date my_emotion_date) {
        List<My_emotionDto> myEmotions = my_emotionService.listOfMyEmotionListByDate(my_emotion_date);
        return ResponseEntity.ok(myEmotions);

    }

}
