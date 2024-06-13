package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.Mc_userDto;
import com.busanit.mentalCare.model.Mc_user;
import com.busanit.mentalCare.repository.Mc_userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

// 스프링 시큐리티의 클래스를 상속받지 않는 UserService
@Service
@RequiredArgsConstructor
public class Mc_userService {

    private final Mc_userRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    // 로그인
    public Mc_userDto loginUser(String user_id, String user_pw) {
        Mc_user user = userRepository.loginUser(user_id, user_pw);

        // 사용자가 없는 경우 예외발생
        if (user == null) {
            throw new UsernameNotFoundException("Id 또는 비밀번호가 일치하지 않습니다.");
        }

        return user.toDto();
    }

    // Id로 유저 정보 반환
    public Mc_userDto findByUserId(String user_id) {
        return userRepository.findByUserId(user_id).toDto();
    }

    // 회원 정보 수정
    public Mc_userDto updateUser(String user_id, Mc_userDto updateUser) {
        Mc_user user = userRepository.findByUserId(user_id);

        if (user != null) {
            if (updateUser.getUser_pw() != null) {
                user.setUser_pw(passwordEncoder.encode(updateUser.getUser_pw()));
            }
            if (updateUser.getUser_nickname() != null) {
                user.setUser_nickname(updateUser.getUser_nickname());
            }
            if (updateUser.getUser_gender() != 0) {
                user.setUser_gender(updateUser.getUser_gender());
            }
            if (true) {
                user.setUser_age(updateUser.getUser_age());
            }
            if (updateUser.getUser_email() != null) {
                user.setUser_email(updateUser.getUser_email());
            }
            if (updateUser.getUser_phonenumber() != null) {
                user.setUser_phonenumber(updateUser.getUser_phonenumber());
            }
        }

        return userRepository.save(user).toDto();
    }

    // 회원 가입
    public Mc_userDto saveUser(Mc_user user) {
        // 패스워드를 암호화하여 저장
        user.setUser_pw(passwordEncoder.encode(user.getUser_pw()));
        return userRepository.save(user).toDto();
    }

    // 회원 탈퇴
    public Mc_userDto withdrawUser(Mc_user user) {
        user.setUser_secession(true);
        return userRepository.save(user).toDto();
    }

    // 전체 회원 조회
    public List<Mc_userDto> listOfAllUsers() {
        List<Mc_user> users = userRepository.findAll();
        return users.stream().map(Mc_user::toDto).toList();
    }

    // 이용 회원 조회
    public List<Mc_userDto> listOfJoinUsers(){
        List<Mc_user> users = userRepository.listOfJoinUsers();
        return users.stream().map(Mc_user::toDto).toList();
    }

    // 탈퇴 회원 조회
    public List<Mc_userDto> listOfWithdrawUser() {
        List<Mc_user> users = userRepository.listOfWithdrawUsers();
        return users.stream().map(Mc_user::toDto).toList();
    }


}
