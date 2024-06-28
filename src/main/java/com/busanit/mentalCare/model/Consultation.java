package com.busanit.mentalCare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Builder
@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultationId")
    private Long consultationId;
    @Column(name = "consultationDetails")
    private String consultationDetails;
    @Column(name = "myChange")
    private String myChange;
    @Column(name = "picture")
    private Blob picture;


    @OneToOne
    @JoinColumn(name = "reservationId")
    private Reservation reservation;

}
