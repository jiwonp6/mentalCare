package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.ReservationDTO;
import com.busanit.mentalCare.model.Hospital;
import com.busanit.mentalCare.model.Reservation;
import com.busanit.mentalCare.repository.HospitalRepository;
import com.busanit.mentalCare.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private HospitalRepository hospitalRepository;

    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }
    public Reservation getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId).orElse(null);
    }

    // 병원 ID를 받아서 예약 정보 들고오기
    @Transactional
    public ReservationDTO createReservation(ReservationDTO dto) {
        String hospitalId = dto.getHospitalId();
        Hospital hospital = hospitalRepository.findById(hospitalId).orElse(null);
        Reservation entity = dto.toEntity(hospital);
        Reservation save = reservationRepository.save(entity);
        return save.toDTO();


    }

    @Transactional
    public Reservation updateReservation(Long reservationId, Reservation updateReservation) {
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

    @Transactional
    public boolean deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null) {
            reservationRepository.delete(reservation);
            return true;
        } else {
            return false;
        }
    }
}
