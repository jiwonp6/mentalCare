package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mc_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = true)
    private char userGender;

    @Column(nullable = false)
    private int userAge;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userPhonenumber;


    //@Column(nullable = false)
    //private String user_joindate;

//    @Column(nullable = false)
//    private boolean user_secession;

    // 엔티티 -> DTO 변환 메서드
    public UserDto toDto() {
        return new UserDto(userId, userPw, userNickname, userGender, userAge, userEmail, userPhonenumber);
    }

}
