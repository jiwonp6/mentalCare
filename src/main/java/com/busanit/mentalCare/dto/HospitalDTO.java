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
    private String hospitalLocation;
    private String hospitalWebsite;
    private String hospitalCall;
    private Time monStartTime;
    private Time monEndTime;
    private Time tueStartTime;
    private Time tueEndTime;
    private Time wedStartTime;
    private Time wedEndTime;
    private Time thuStartTime;
    private Time thuEndTime;
    private Time friStartTime;
    private Time friEndTime;
    private Time satStartTime;
    private Time satEndTime;
    private Time sunStartTime;
    private Time sunEndTime;
    private String sunHoliday;
    private Time lunchtime;
    private String holiday;


    public Hospital toEntity() {
        return Hospital.builder()
                .hospitalId(hospitalId)
                .hospitalName(hospitalName)
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
