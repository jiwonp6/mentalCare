package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.SleepDataDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "sleepData")
public class SleepData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sldId;

    @ManyToOne(targetEntity = com.busanit.mentalCare.model.McUser.class)
    @JoinColumn(name = "userId")
    private McUser mcUser;

    @Column(nullable = false)
    private String sldDate;

    @Column(nullable = false)
    private int sldAvg;

    public SleepDataDto toDto() {
        return new SleepDataDto(sldId, mcUser.getUserId(), sldDate, sldAvg);
    }

}
