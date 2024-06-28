package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.McUser;
import com.busanit.mentalCare.model.SleepData;
import com.busanit.mentalCare.model.StressData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SleepDataDto {
    private Long sldId;

    private String userId;

    private String sldDate;

    private int sldAvg;

    // DTO -> 엔티티 (엔티티에 @Builder 적용, 빌더 패턴 사용)
    public SleepData toEntity(McUser mcUser) {
        // DTO -> 엔티티 필드 매핑
        SleepData sld = SleepData.builder()
                .sldId(sldId)
                .mcUser(mcUser)
                .sldDate(sldDate)
                .sldAvg(sldAvg)
                .build();

        return sld;
    }
}
