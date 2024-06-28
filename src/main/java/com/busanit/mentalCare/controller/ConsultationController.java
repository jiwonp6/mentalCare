package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.model.Consultation;
import com.busanit.mentalCare.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    private ConsultationRepository consultationRepository;

    @PostMapping
    public Consultation createConsultation(@RequestBody Consultation consultation) {
        System.out.println(consultation);
        Consultation save = consultationRepository.save(consultation);

        return consultationRepository.save(consultation);
    }

    @GetMapping
    public List<Consultation> getAllConsultation() { return consultationRepository.findAll(); }

    @GetMapping("/{id}")
    public Consultation getConsultationById(@PathVariable Long consultationId) {
        return consultationRepository.findById(consultationId).orElse(null);
    }

    @PutMapping("/{id}")
    public Consultation updateConsultation(@PathVariable Long consultationId, @RequestBody Consultation updateConsultation) {
        Consultation consultation = consultationRepository.findById(consultationId).orElse(null);
        if (consultation != null ) {
            if (updateConsultation.getConsultationDetails() == null) {
                consultation.setConsultationDetails(updateConsultation.getConsultationDetails());
            }
            if (updateConsultation.getMyChange() == null ) {
                consultation.setMyChange(updateConsultation.getMyChange());
            }
            return consultationRepository.save(consultation);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteConsultataion(@PathVariable Long consultationId) {
        Consultation consultation = consultationRepository.findById(consultationId).orElse(null);
        if (consultation != null) {
            consultationRepository.delete(consultation);
            return "성공적으로 삭제되었습니다.";
        } else {
            return "존재하지 않는 예약정보입니다.";
        }

    }

}
