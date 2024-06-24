package com.busanit.mentalCare.service;

import com.busanit.mentalCare.model.Consultation;
import com.busanit.mentalCare.repository.ConsultationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    public List<Consultation> getAllConsultation() { return consultationRepository.findAll(); }

    public Consultation getConsultationById(Long consultationId) {
        return consultationRepository.findById(consultationId).orElse(null);
    }

    @Transactional
    public Consultation createconsultation (Consultation consultation) { return consultationRepository.save(consultation); }

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
