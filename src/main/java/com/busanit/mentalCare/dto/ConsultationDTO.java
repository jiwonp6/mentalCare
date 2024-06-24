package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.Consultation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDTO {
    private Long consultationId;
    private String consultationDetails;
    private String myChange;

    public Consultation toEntity() {
        return Consultation.builder()
                .consultationId(consultationId)
                .consultationDetails(consultationDetails)
                .myChange(myChange)
                .build();
    }
}
