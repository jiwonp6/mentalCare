package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.Consultation;
import com.busanit.mentalCare.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDTO {
    private Long reservationId;
    private Long consultationId;
    private String consultationDetails;
    private String myChange;
    private Blob picture;

    public Consultation toEntity(Reservation reservation) {
        return Consultation.builder()
                .consultationId(consultationId)
                .consultationDetails(consultationDetails)
                .myChange(myChange)
                .picture(picture)
                .reservation(reservation)
                .build();
    }
}
