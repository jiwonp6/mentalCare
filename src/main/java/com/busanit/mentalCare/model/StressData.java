package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.StressDataDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "stressData")
public class StressData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stdId;

    @ManyToOne(targetEntity = com.busanit.mentalCare.model.McUser.class)
    @JoinColumn(name = "userId")
    private McUser mcUser;

    @Column(nullable = false)
    private String stdDate;

    @Column(nullable = false)
    private int stdAvg;

    // 엔티티 -> DTO 변환 메서드
    public StressDataDto toDto() {
        return new StressDataDto(stdId, mcUser.getUserId(), stdDate, stdAvg);
    }

}
