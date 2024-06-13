package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.My_emotion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class My_emotionDto {
    private int my_emotion_id;
    private String user_id;
    private int emotion_id;
    private String my_emotion_reason;
    private Date my_emotion_date;

    // DTO -> 엔티티 (엔티티에 @Builder 적용, 빌더 패턴 사용)
    public My_emotion toEntity() {
        // DTO -> 엔티티 필드 매핑
        My_emotion my_emotion = My_emotion.builder()
                .my_emotion_id(my_emotion_id)
                .user_id(user_id)
                .emotion_id(emotion_id)
                .my_emotion_reason(my_emotion_reason)
                .my_emotion_date(my_emotion_date)
                .build();

        return my_emotion;
    }
}
