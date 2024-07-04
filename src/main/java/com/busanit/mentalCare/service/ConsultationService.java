package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.ConsultationDTO;
import com.busanit.mentalCare.model.Consultation;
import com.busanit.mentalCare.model.Reservation;
import com.busanit.mentalCare.repository.ConsultationRepository;
import com.busanit.mentalCare.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    private ConsultationRepository consultationRepository;

    public List<Consultation> getAllConsultation() { return consultationRepository.findAll(); }

    public Consultation getConsultationById(Long consultationId) {
        return consultationRepository.findById(consultationId).orElse(null);
    }

    @Transactional
    public ConsultationDTO createConsultation (ConsultationDTO consultationDto) {
        Long reservationId = consultationDto.getReservationId();
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        Consultation entity = consultationDto.toEntity(reservation);
        Consultation save = consultationRepository.save(entity);

        reservation.setConsultationId(save.getConsultationId());    // 상담내역 저장시 예약정보의 상담ID 업데이트
        return save.toDTO();
    }

    @Transactional
    public Consultation updateConsultation(Long consultationId, Consultation updateConsultation) {
        Consultation consultation = consultationRepository.findById(consultationId).orElse(null);
        if (consultation != null) {
            if (updateConsultation.getConsultationDetails() != null) {
                consultation.setConsultationDetails(updateConsultation.getConsultationDetails());
            }
            if (updateConsultation.getMyChange() == null ) {
                consultation.setMyChange(updateConsultation.getMyChange());
            }
            return consultationRepository.save(consultation);
        } else  {
            return null;
        }

    }

    @Transactional
    public boolean deleteConsultation(Long consultationId) {
        Consultation consultation = consultationRepository.findById(consultationId).orElse(null);
        if (consultation != null) {
            consultationRepository.delete(consultation);
            return true;
        } else {
            return false;
        }
    }
}
