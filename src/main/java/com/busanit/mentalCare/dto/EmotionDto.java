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

    public Emotion toEntity() {
        Emotion emotion = Emotion.builder()
                .emotionId(emotionId)
                .emotionWord(emotionWord)
                .emotionType(emotionType)
                .build();

        return emotion;
    }
}
