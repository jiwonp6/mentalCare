package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.Emotion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmotionDto {
    private int emotion_id;
    private String emotion_keyword;

    // DTO -> 엔티티 (엔티티에 @Builder 적용, 빌더 패턴 사용)
    public Emotion toEntity() {
        // DTO -> 엔티티 필드 매핑
        Emotion emotion = Emotion.builder()
                .emotion_id(emotion_id)
                .emotion_keyword(emotion_keyword)
                .build();

        return emotion;
    }
}
