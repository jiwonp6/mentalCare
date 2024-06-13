package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.Mc_userDto;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "mc_user")
public class Mc_user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String user_id;

    @Column(nullable = false)
    private String user_pw;

    @Column(nullable = false)
    private String user_nickname;

    @Column(nullable = true)
    private char user_gender;

    @Column(nullable = false)
    private int user_age;

    @Column(nullable = false)
    private String user_email;

    @Column(nullable = false)
    private String user_phonenumber;

    @Column(nullable = false)
    private Date user_joindate;

    @Column(nullable = false)
    private boolean user_secession;

    // 엔티티 -> DTO 변환 메서드
    public Mc_userDto toDto() {
        return new Mc_userDto(user_id, user_pw, user_nickname, user_gender, user_age, user_email, user_phonenumber, user_joindate, user_secession);
    }

}
