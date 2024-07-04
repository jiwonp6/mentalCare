package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.dto.ReservationDTO;
import com.busanit.mentalCare.model.McUser;
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
        System.out.println(reservationDTO);
        return reservationService.createReservation(reservationDTO);
    }

    @GetMapping
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @GetMapping("/{reservationId}")
    public ReservationDTO getReservationById(@PathVariable Long reservationId) {
        return reservationService.getReservationById(reservationId);
    }
    @GetMapping("/user/{userId}")
    public List<ReservationDTO> getReservationByUserId(@PathVariable String userId) {
        return reservationService.getReservationByUserId(userId);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long reservationId, @RequestBody Reservation updateReservation) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null) {
            if (updateReservation.getReservationDate() == null) {
                reservation.setReservationDate(updateReservation.getReservationDate());
            }
            if (updateReservation.getReservationTime() == null) {
                reservation.setReservationTime(updateReservation.getReservationTime());
            }
            return reservationRepository.save(reservation);
        } else {
            return null;
        }
    }

    // DELETE
    @DeleteMapping("/{reservationId}")
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
