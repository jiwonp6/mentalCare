package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.McUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class McUserDto {
    private String userId;
    private String userPw;
    private String userNickname;
    private char userGender;
    private String userBirth;
    private String userEmail;
    private String userPhonenumber;
    private LocalDate userJoindate;
    private boolean userSecession;

    // DTO -> 엔티티 (엔티티에 @Builder 적용, 빌더 패턴 사용)
    public McUser toEntity() {
        // DTO -> 엔티티 필드 매핑
        McUser user = McUser.builder()
                .userId(userId)
                .userPw(userPw)
                .userNickname(userNickname)
                .userGender(userGender)
                .userBirth(userBirth)
                .userEmail(userEmail)
                .userPhonenumber(userPhonenumber)
                .userJoindate(userJoindate)
                .userSecession(userSecession)
                .build();

        return user;
    }
}
