package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.dto.EmotionDiaryDto;
import com.busanit.mentalCare.dto.EmotionDiaryViewDto;
import com.busanit.mentalCare.service.EmotionDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/emotionDiary")
@RestController
public class EmotionDiaryController {
    @Autowired
    EmotionDiaryService emotionDiaryService;
    
    /* 메소드 */
    // 오늘 감정일지
    @GetMapping("/getTodayEmotionDiary")
    public ResponseEntity<EmotionDiaryDto> getTodayEmotionDiary(@RequestParam String userId) {
        EmotionDiaryDto emotionDiaryDto = emotionDiaryService.getTodayEmotionDiary(userId);
        return ResponseEntity.ok(emotionDiaryDto);
    }

    // 해당 날짜 감정일지
    @GetMapping("/getSelectedDateEmotionDiary")
    public ResponseEntity<EmotionDiaryViewDto> getSelectedDateEmotionDiary(@RequestParam String userId, @RequestParam String edDate) {
        EmotionDiaryViewDto edViewDto = emotionDiaryService.getSelectedDateEmotionDiary(userId, edDate);
        return ResponseEntity.ok(edViewDto);
    }

    // 감정일지 작성
    @PostMapping("/writeEmotionDiary")
    public ResponseEntity<EmotionDiaryDto> writeEmotionDiary(@RequestParam String userId, @RequestBody EmotionDiaryDto edDto) {
        EmotionDiaryDto emotionDiaryDto = emotionDiaryService.writeEmotionDiary(userId, edDto);
        return ResponseEntity.ok(emotionDiaryDto);
    }

    // 감정일지 수정
    @PutMapping("/updateEmotionDiary")
    public ResponseEntity<EmotionDiaryDto> updateEmotionDiary(@RequestParam Long edId, @RequestBody EmotionDiaryDto updateEdDto) {
        EmotionDiaryDto emotionDiaryDto = emotionDiaryService.updateEmotionDiary(edId, updateEdDto);
        return ResponseEntity.ok(emotionDiaryDto);
    }

    // 감정일지 삭제
    @PutMapping("/deleteEmotionDiary")
    public ResponseEntity<Integer> deleteEmotionDiary(@RequestParam String userId, @RequestParam Long edId) {
        int result = emotionDiaryService.deleteEmotionDiary(userId, edId);
        try {
            if (result > 0) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.ok(result);
            }
        } catch (Exception e) {
            throw new RuntimeException("나의 이모션 삭제 시 오류 발생");
        }
    }

}
