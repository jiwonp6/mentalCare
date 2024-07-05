package com.busanit.mentalCare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmotionDiaryViewDto {
    private Long edId;
    private String userId;
    private Long emotionId;
    private String emotionWord;
    private String emotionType;
    private String edReason;
    private String edDate;


}
