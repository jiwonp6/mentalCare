package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.ReservationDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;

    public ReservationDTO toDTO() {
        String hospitalId = null;
        if (hospital != null) {
            hospitalId = hospital.getHospitalId();
            return new ReservationDTO(reservationId, userId, reservationDate, treatmentDate, treatmentTime, hospitalId);
        }
        return null;
    }
}
