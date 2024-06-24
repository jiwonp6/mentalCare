package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.HospitalDTO;
import com.busanit.mentalCare.dto.ReservationDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

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
public class Hospital {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospitalId")
    private String hospitalId;
    @Column(name = "hospitalName")
    private String hospitalName;
    @Column(name = "hospitalLocation")
    private String hospitalLocation;
    @Column(name = "hospitalWebsite")
    private String hospitalWebsite;
    @Column(name = "hospitalCall")
    private String hospitalCall;
    @Column(name = "monStartTime")
    private Time monStartTime;
    @Column(name = "monEndTime")
    private Time monEndTime;
    @Column(name = "tueStartTime")
    private Time tueStartTime;
    @Column(name = "tueEndTime")
    private Time tueEndTime;
    @Column(name = "wedStartTime")
    private Time wedStartTime;
    @Column(name = "wedEndTime")
    private Time wedEndTime;
    @Column(name = "thuStartTime")
    private Time thuStartTime;
    @Column(name = "thuEndTime")
    private Time thuEndTime;
    @Column(name = "friStartTime")
    private Time friStartTime;
    @Column(name = "friEndTime")
    private Time friEndTime;
    @Column(name = "satStartTime")
    private Time satStartTime;
    @Column(name = "satEndTime")
    private Time satEndTime;
    @Column(name = "sunStartTime")
    private Time sunStartTime;
    @Column(name = "sunEndTime")
    private Time sunEndTime;
    @Column(name = "sunHoliday")
    private String sunHoliday;
    @Column(name = "lunchtime")
    private Time lunchtime;
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

