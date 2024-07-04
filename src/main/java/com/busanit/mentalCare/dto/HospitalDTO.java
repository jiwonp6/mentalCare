package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.Hospital;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDTO {
    private String hospitalId;
    private String hospitalName;
    private String hospitalLocCode;
    private String hospitalLocation;
    private String hospitalWebsite;
    private String hospitalCall;
    private String monStartTime;
    private String monEndTime;
    private String tueStartTime;
    private String tueEndTime;
    private String wedStartTime;
    private String wedEndTime;
    private String thuStartTime;
    private String thuEndTime;
    private String friStartTime;
    private String friEndTime;
    private String satStartTime;
    private String satEndTime;
    private String sunStartTime;
    private String sunEndTime;
    private String sunHoliday;
    private String lunchtime;
    private String holiday;

    public Hospital toEntity() {
        return Hospital.builder()
                .hospitalId(hospitalId)
                .hospitalName(hospitalName)
                .hospitalLocCode(hospitalLocCode)
                .hospitalLocation(hospitalLocation)
                .hospitalWebsite(hospitalWebsite)
                .hospitalCall(hospitalCall)
                .monStartTime(monStartTime)
                .monEndTime(monEndTime)
                .tueStartTime(tueStartTime)
                .tueEndTime(tueEndTime)
                .wedStartTime(wedStartTime)
                .wedEndTime(wedEndTime)
                .thuStartTime(thuStartTime)
                .thuEndTime(thuEndTime)
                .friStartTime(friStartTime)
                .friEndTime(friEndTime)
                .satStartTime(satStartTime)
                .satEndTime(satEndTime)
                .sunHoliday(sunHoliday)
                .lunchtime(lunchtime)
                .holiday(holiday)
                .build();
    }
}
