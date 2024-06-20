package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.MyEmotionDto;
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
@Table(name = "myEmotion")
public class MyEmotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myEmotionId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = true)
    private int emotionId;

    @Column(nullable = true)
    private String myEmotionReason;

    @Column(nullable = false)
    private Date myEmotionDate;

    // 엔티티 -> DTO 변환 메서드
    public MyEmotionDto toDto() {
        return new MyEmotionDto(myEmotionId, userId, emotionId, myEmotionReason, myEmotionDate);
    }
}
