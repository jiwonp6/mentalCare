package com.busanit.mentalCare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class McUserLoginSuccessDto {
    private String userId;
    private String userPw;
    private String userNickname;
    private char userGender;
    private String userBirth;
    private String userEmail;
    private String userPhonenumber;
    private boolean userSecession;
    private String jwt;
}
