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

    public StressData toEntity(McUser mcUser) {
        StressData std = StressData.builder()
                .stdId(stdId)
                .mcUser(mcUser)
                .stdDate(stdDate)
                .stdAvg(stdAvg)
                .build();

        return std;
    }
}
