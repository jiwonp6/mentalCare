package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.HospitalDTO;
import com.busanit.mentalCare.dto.ReservationDTO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/*
    hospital_id VARCHAR(300),
    hospital_name VARCHAR(255),
    hospital_location VARCHAR(255),
    hospital_website VARCHAR(255),
    hospital_call VARCHAR(255),
    mon_start_time TIME,
    mon_end_time TIME,
    tue_start_time TIME,
    tue_end_time TIME,
    wed_start_time TIME,
    wed_end_time TIME,
    thu_start_time TIME,
    thu_end_time TIME,
    fri_start_time TIME,
    fri_end_time TIME,
    sat_start_time TIME,
    sat_end_time TIME,
    sun_start_time TIME,
    sun_end_time TIME,
    sun_holiday VARCHAR(255),
    lunchtime VARCHAR(300),
    holiday VARCHAR(300),
 */
@Data
@Builder
@Entity
@Table(name = "hospital")
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {

    @Id
    @Column(name = "hospital_id")
    private String hospitalId;
    @Column(name = "hospital_name")
    private String hospitalName;
    @Column(name = "hospital_location")
    private String hospitalLocation;
    @Column(name = "hospitalWebsite")
    private String hospitalWebsite;
    @Column(name = "hospitalCall")
    private String hospitalCall;
    @Column(name = "monStartTime")
    private String monStartTime;
    @Column(name = "monEndTime")
    private String monEndTime;
    @Column(name = "tueStartTime")
    private String tueStartTime;
    @Column(name = "tueEndTime")
    private String tueEndTime;
    @Column(name = "wedStartTime")
    private String wedStartTime;
    @Column(name = "wedEndTime")
    private String wedEndTime;
    @Column(name = "thuStartTime")
    private String thuStartTime;
    @Column(name = "thuEndTime")
    private String thuEndTime;
    @Column(name = "friStartTime")
    private String friStartTime;
    @Column(name = "friEndTime")
    private String friEndTime;
    @Column(name = "satStartTime")
    private String satStartTime;
    @Column(name = "satEndTime")
    private String satEndTime;
    @Column(name = "sunStartTime")
    private String sunStartTime;
    @Column(name = "sunEndTime")
    private String sunEndTime;
    @Column(name = "sunHoliday")
    private String sunHoliday;
    @Column(name = "lunchtime")
    private String lunchtime;
    @Column(name = "holiday")
    private String holiday;

    //  양방향일때
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    public HospitalDTO toDTO() {
        List<ReservationDTO> reservationDTOList = new ArrayList<>();
        if (reservations != null) {
            reservationDTOList = reservations.stream()
                    .map(Reservation::toDTO)
                    .toList();
        }
        return new HospitalDTO(hospitalId, hospitalName, hospitalLocation, hospitalWebsite, hospitalCall, monStartTime,
                monEndTime, tueStartTime, tueEndTime, wedStartTime, wedEndTime, thuStartTime, thuEndTime, friStartTime,
                friEndTime, satStartTime, satEndTime, sunStartTime, sunEndTime, sunHoliday, lunchtime, holiday);
    }
}
