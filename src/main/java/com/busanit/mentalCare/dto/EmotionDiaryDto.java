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
public class EmotionDiaryDto {
    private Long edId;
    private String userId;
    private Long emotionId;
    private String edReason;
    private String edDate;

    public EmotionDiary toEntity(McUser mcUser, Emotion emotion) {
        EmotionDiary myEmotion = EmotionDiary.builder()
                                        .edId(edId)
                                        .mcUser(mcUser)
                                        .emotion(emotion)
                                        .edReason(edReason)
                                        .edDate(edDate)
                                        .build();
        return myEmotion;
    }
}
