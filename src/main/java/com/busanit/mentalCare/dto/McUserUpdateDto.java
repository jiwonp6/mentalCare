package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.McUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class McUserUpdateDto {
    private String userId;
    private String userPw;
    private String userPwNew;
    private String userNickname;
    private char userGender;
    private String userBirth;
    private String userEmail;
    private String userPhonenumber;
    private LocalDate userJoindate;
    private boolean userSecession;

    public McUser toEntity() {
        McUser user = McUser.builder()
                .userId(userId)
                .userPw(userPwNew)
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
