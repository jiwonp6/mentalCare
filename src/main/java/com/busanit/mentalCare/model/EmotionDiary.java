package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.EmotionDiaryDto;
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
@Table(name = "emotionDiary")
public class EmotionDiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long edId;

    @ManyToOne(targetEntity = com.busanit.mentalCare.model.McUser.class)
    @JoinColumn(name = "userId")
    private McUser mcUser;


    @ManyToOne(targetEntity = Emotion.class)
    @JoinColumn(name = "emotionId")
    private Emotion emotion;

    @Column(nullable = true)
    private String edReason;

    @Column(nullable = false)
    private String edDate;

    // 엔티티 -> DTO 변환 메서드
    public EmotionDiaryDto toDto() {
        return new EmotionDiaryDto(edId, mcUser.getUserId(), emotion.getEmotionId(), edReason, edDate);
    }
}
