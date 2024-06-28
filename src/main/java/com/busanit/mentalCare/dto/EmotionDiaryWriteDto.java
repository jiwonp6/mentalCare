package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.Emotion;
import com.busanit.mentalCare.model.EmotionDiary;
import com.busanit.mentalCare.model.McUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmotionDiaryWriteDto {
    private String userId;
    private Long emotionId;
    private String edReason;
    private String edDate;

    // DTO -> 엔티티 (엔티티에 @Builder 적용, 빌더 패턴 사용)
    public EmotionDiary toEntity(McUser mcUser, Emotion emotion) {
        // DTO -> 엔티티 필드 매핑
        EmotionDiary myEmotion = EmotionDiary.builder()
                                        .mcUser(mcUser)
                                        .emotion(emotion)
                                        .edReason(edReason)
                                        .edDate(edDate)
                                        .build();

        return myEmotion;
    }
}
