package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.dto.MyEmotionDto;
import com.busanit.mentalCare.model.MyEmotion;
import com.busanit.mentalCare.service.MyEmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class MyEmotionController {
    @Autowired
    MyEmotionService myEmotionService;

    
    /* 메소드 */
    // 내 이모션 추가
    @PostMapping("/createMyEmotion")
    public ResponseEntity<String> createMyEmotion(@RequestBody MyEmotionDto myEmotionDto) {
        myEmotionService.createMyEmotion(myEmotionDto);
        return ResponseEntity.ok("나의 이모션 추가 완료");
    }

    // 내 이모션 수정
    @GetMapping("/updateMyEmotion")
    public ResponseEntity<String> updateMyEmotion(@RequestBody Long myEmotionId, @RequestBody MyEmotionDto myEmotionDto) {
        myEmotionService.updateMyEmotion(myEmotionId, myEmotionDto);
        return ResponseEntity.ok("나의 이모션 수정 완료");
    }

    // 내 이모션 삭제
    @PostMapping("/deleteMyEmotion")
    public ResponseEntity<String> deleteMyEmotion(@RequestBody Long myEmotionId) {
        int result = myEmotionService.deleteMyEmotion(myEmotionId);
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
    public ResponseEntity<List<MyEmotionDto>> listOfMyEmotionListByDate(@RequestBody Date myEmotionDate) {
        List<MyEmotionDto> myEmotions = myEmotionService.listOfMyEmotionListByDate(myEmotionDate);
        return ResponseEntity.ok(myEmotions);

    }

}
