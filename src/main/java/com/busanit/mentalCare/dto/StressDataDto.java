package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.McUser;
import com.busanit.mentalCare.model.StressData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StressDataDto {
    private Long stdId;

    private String userId;

    private String stdDate;

    private int stdAvg;

    // DTO -> 엔티티 (엔티티에 @Builder 적용, 빌더 패턴 사용)
    public StressData toEntity(McUser mcUser) {
        // DTO -> 엔티티 필드 매핑
        StressData std = StressData.builder()
                .stdId(stdId)
                .mcUser(mcUser)
                .stdDate(stdDate)
                .stdAvg(stdAvg)
                .build();

        return std;
    }
}
