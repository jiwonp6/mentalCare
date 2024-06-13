package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.EmotionDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "emotion_state")
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emotion_id;

    @Column(nullable = false)
    private String emotion_keyword;

    // 엔티티 -> DTO 변환 메서드
    public EmotionDto toDto() {
        return new EmotionDto(emotion_id, emotion_keyword);
    }
}
