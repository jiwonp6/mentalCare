package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.Mc_user;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mc_userDto {
    private String user_id;
    private String user_pw;
    private String user_nickname;
    private char user_gender;
    private int user_age;
    private String user_email;
    private String user_phonenumber;
    private Date user_joindate;
    private boolean user_secession;

    // DTO -> 엔티티 (엔티티에 @Builder 적용, 빌더 패턴 사용)
    public Mc_user toEntity() {
        // DTO -> 엔티티 필드 매핑
        Mc_user user = Mc_user.builder()
                .user_pw(user_pw)
                .user_nickname(user_nickname)
                .user_gender(user_gender)
                .user_age(user_age)
                .user_email(user_email)
                .user_phonenumber(user_phonenumber)
                .user_secession(user_secession)
                .build();

        return user;
    }
}
