package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.dto.ReservationDTO;
import com.busanit.mentalCare.model.Reservation;
import com.busanit.mentalCare.repository.ReservationRepository;
import com.busanit.mentalCare.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping
    public ReservationDTO createReservation(@RequestBody ReservationDTO reservationDTO) {
        return reservationService.createReservation(reservationDTO);
    }

    @GetMapping
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long reservationId) {
        return reservationRepository.findById(reservationId).orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long reservationId, @RequestBody Reservation updateReservation) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null) {
            if (updateReservation.getReservationDate() == null) {
                reservation.setReservationDate(updateReservation.getReservationDate());
            }
            if (updateReservation.getTreatmentDate() == null) {
                reservation.setTreatmentDate(updateReservation.getTreatmentDate());
            }
            if (updateReservation.getTreatmentTime() == null) {
                reservation.setTreatmentTime(updateReservation.getTreatmentTime());
            }
            return reservationRepository.save(reservation);
        } else {
            return null;
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteReservation(@PathVariable Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null) {
            reservationRepository.delete(reservation);
            return "성공적으로 삭제되었습니다.";
        } else {
            return "존재하지 않는 예약정보입니다.";
        }

    }

}
