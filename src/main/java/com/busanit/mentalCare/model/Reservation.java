package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.ReservationDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservationId")
    private Long reservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private McUser user;

    @Column(name = "consultationId")
    private Long consultationId;

    @Temporal(TemporalType.DATE)
    @Column(name = "reservationDate")
    private LocalDate reservationDate;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIME)
    @Column(name = "reservationTime")
    private LocalTime reservationTime;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;

    public ReservationDTO toDTO() {
        String hospitalId = null;
        String hospitalName = null;
        if (hospital != null) {
            hospitalId = hospital.getHospitalId();
            hospitalName = hospital.getHospitalName();
            return new ReservationDTO(reservationId, user.getUserId(), consultationId, reservationDate, reservationTime, hospitalId, hospitalName);
        }
        return null;
    }
}
