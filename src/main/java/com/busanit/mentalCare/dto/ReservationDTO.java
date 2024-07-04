package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.Hospital;
import com.busanit.mentalCare.model.McUser;
import com.busanit.mentalCare.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Long reservationId;
    private String userId;
    private Long consultationId;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate reservationDate;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime ReservationTime;
    private String hospitalId;
    private String hospitalName;

    public Reservation toEntity(Hospital hospital, McUser user) {
        return Reservation.builder()
                .reservationId(reservationId)
                .user(user)
                .consultationId(consultationId)
                .reservationDate(reservationDate)
                .reservationTime(ReservationTime)
                .hospital(hospital)
                .build();
    }


}
