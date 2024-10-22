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
    private String emotionWord;

    @Column(nullable = false)
    private String emotionType;

    public EmotionDto toDto() {
        return new EmotionDto(emotionId, emotionWord, emotionType);
    }
}
