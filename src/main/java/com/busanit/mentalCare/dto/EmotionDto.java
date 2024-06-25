package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.Emotion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmotionDto {
    private Long emotionId;
    private String emotionWord;
    private String emotionType;

    // DTO -> 엔티티 (엔티티에 @Builder 적용, 빌더 패턴 사용)
    public Emotion toEntity() {
        // DTO -> 엔티티 필드 매핑
        Emotion emotion = Emotion.builder()
                .emotionId(emotionId)
                .emotionWord(emotionWord)
                .emotionType(emotionType)
                .build();

        return emotion;
    }
}
