package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.McUserDto;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "mcUser")
public class McUser {
    @Id
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = true)
    private char userGender;

    @Column(nullable = false)
    private String userBirth;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userPhonenumber;

    @Column(nullable = false)
    private LocalDate userJoindate;

    @Column(nullable = false)
    private boolean userSecession;

    // 엔티티 -> DTO 변환 메서드
    public McUserDto toDto() {
        return new McUserDto(userId, userPw, userNickname, userGender, userBirth, userEmail, userPhonenumber, userJoindate, userSecession);
    }

}
