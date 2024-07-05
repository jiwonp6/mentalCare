package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.McUser;
import com.busanit.mentalCare.model.SleepData;
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

    public SleepData toEntity(McUser mcUser) {
        SleepData sld = SleepData.builder()
                .sldId(sldId)
                .mcUser(mcUser)
                .sldDate(sldDate)
                .sldAvg(sldAvg)
                .build();

        return sld;
    }
}
