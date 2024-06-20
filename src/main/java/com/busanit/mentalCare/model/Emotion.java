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
@Table(name = "emotion")
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emotionId;

    @Column(nullable = false)
    private String emotionKeyword;

    // 엔티티 -> DTO 변환 메서드
    public EmotionDto toDto() {
        return new EmotionDto(emotionId, emotionKeyword);
    }
}
