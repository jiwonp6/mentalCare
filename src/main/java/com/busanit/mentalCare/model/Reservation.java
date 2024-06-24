package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.ReservationDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservationId")
    private Long reservationId;
    @Column(name = "userId")
    private String userId;
    @Column(name = "reservationDate")
    private LocalDate reservationDate;
    @Column(name = "treatmentDate")
    private LocalDate treatmentDate;
    @Column(name = "treatmentTime")
    private LocalTime treatmentTime;
    @Column(name = "hospitalName")
    private String hospitalName;

    @ManyToOne
    @JoinColumn(name = "hospitalName")
    private Hospital hospital;

    public ReservationDTO toDTO() {
        String hospitalId = String.valueOf(0L);
        if (hospital != null) {
            hospitalId = hospital.getHospitalId();
        }
        return  new ReservationDTO(reservationId, userId, reservationDate, treatmentDate, treatmentTime);
    }
}

