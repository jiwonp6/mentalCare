package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.ReservationDTO;
import com.busanit.mentalCare.model.Hospital;
import com.busanit.mentalCare.model.McUser;
import com.busanit.mentalCare.model.Reservation;
import com.busanit.mentalCare.repository.HospitalRepository;
import com.busanit.mentalCare.repository.McUserRepository;
import com.busanit.mentalCare.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private McUserRepository mcUserRepository;

    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }
    public ReservationDTO getReservationById(Long reservationId) {
        System.out.println("reservationId : " + reservationId);
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        return reservation.toDTO();

    }

    // 병원 ID를 받아서 예약 정보 들고오기
    @Transactional
    public ReservationDTO createReservation(ReservationDTO dto) {
        String hospitalId = dto.getHospitalId();
        Hospital hospital = hospitalRepository.findById(hospitalId).orElse(null);
        McUser user = mcUserRepository.findByUserId(dto.getUserId());
        Reservation entity = dto.toEntity(hospital, user);
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
            if (updateReservation.getReservationTime() == null) {
                reservation.setReservationTime(updateReservation.getReservationTime());
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

    public List<ReservationDTO> getReservationByUserId(String userId) {
        List<Reservation> list = reservationRepository.findByUserUserId(userId);
        List<ReservationDTO> dtoList = new ArrayList<>();
        for (Reservation reservation : list) {
            dtoList.add(reservation.toDTO());
        }
        return dtoList;
    }
}
