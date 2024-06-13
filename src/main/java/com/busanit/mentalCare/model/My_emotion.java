package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.My_emotionDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "emotion")
public class My_emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int my_emotion_id;

    @Column(nullable = false)
    private String user_id;

    @Column(nullable = true)
    private int emotion_id;

    @Column(nullable = true)
    private String my_emotion_reason;

    @Column(nullable = false)
    private Date my_emotion_date;

    // 엔티티 -> DTO 변환 메서드
    public My_emotionDto toDto() {
        return new My_emotionDto(my_emotion_id, user_id, emotion_id, my_emotion_reason, my_emotion_date);
    }
}
