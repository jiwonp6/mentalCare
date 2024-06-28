package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userPw;
    private String userNickname;
    private char userGender;
    private int userAge;
    private String userEmail;
    private String userPhonenumber;
    // private String user_joindate;
    // private boolean user_secession;

    // DTO -> 엔티티 (엔티티에 @Builder 적용, 빌더 패턴 사용)
    public User toEntity() {
        // DTO -> 엔티티 필드 매핑
        User user = User.builder()
                .userPw(userPw)
                .userNickname(userNickname)
                .userGender(userGender)
                .userAge(userAge)
                .userEmail(userEmail)
                .userPhonenumber(userPhonenumber)
                .userId(userId)
                //.user_joindate(user_joindate)
                // .user_secession(user_secession)
                .build();
        return user;
    }
}
