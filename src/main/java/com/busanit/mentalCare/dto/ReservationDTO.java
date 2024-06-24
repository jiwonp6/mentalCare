package com.busanit.mentalCare.dto;

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
    private LocalDate reservationDate;
    private LocalDate treatmentDate;
    private LocalTime treatmentTime;

    public Reservation toEntity() {
        return Reservation.builder()
                .reservationId(reservationId)
                .userId(userId)
                .reservationDate(reservationDate)
                .treatmentDate(treatmentDate)
                .treatmentTime(treatmentTime)
                .build();
    }

}
