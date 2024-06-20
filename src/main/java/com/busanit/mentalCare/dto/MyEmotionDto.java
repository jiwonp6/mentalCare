package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.MyEmotion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyEmotionDto {
    private Long myEmotionId;
    private String userId;
    private int emotionId;
    private String myEmotionReason;
    private Date myEmotionDate;

    // DTO -> 엔티티 (엔티티에 @Builder 적용, 빌더 패턴 사용)
    public MyEmotion toEntity() {
        // DTO -> 엔티티 필드 매핑
        MyEmotion my_emotion = MyEmotion.builder()
                .myEmotionId(myEmotionId)
                .userId(userId)
                .emotionId(emotionId)
                .myEmotionReason(myEmotionReason)
                .myEmotionDate(myEmotionDate)
                .build();

        return my_emotion;
    }
}
