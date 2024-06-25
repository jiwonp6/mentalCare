package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.McUserDto;
import com.busanit.mentalCare.dto.McUserUpdateDto;
import com.busanit.mentalCare.model.McUser;
import com.busanit.mentalCare.repository.McUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

// 스프링 시큐리티의 클래스를 상속받지 않는 UserService
@Service
@RequiredArgsConstructor
public class McUserService {

    private final McUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    // 로그인
    public McUserDto loginUser(String userId, String userPw) {
        McUser user = userRepository.loginUser(userId, userPw);

        // 사용자가 없는 경우 예외발생
        if (user == null) {
            throw new UsernameNotFoundException("Id 또는 비밀번호가 일치하지 않습니다.");
        }

        return user.toDto();
    }

    // Id로 유저 정보 반환
    public McUserDto getByUserId(String userId) {
        McUser user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.toDto();
        } else {
            return null;
        }
    }

    // 회원 정보 수정
    public McUserDto updateUser(String userId, McUserUpdateDto userUpdateDto) {
        McUser user = userRepository.findById(userId).orElse(null);
        boolean matches = passwordEncoder.matches(userUpdateDto.getUserPw(), user.getUserPw());

        if (matches) {
            if (userUpdateDto.getUserPwNew() != null && !userUpdateDto.getUserPwNew().equals("")) {
                user.setUserPw(passwordEncoder.encode(userUpdateDto.getUserPwNew()));
            }
            if (userUpdateDto.getUserNickname() != null) {
                user.setUserNickname(userUpdateDto.getUserNickname());
            }
            if (true) {
                user.setUserGender(userUpdateDto.getUserGender());
            }
            if (true) {
                user.setUserBirth(userUpdateDto.getUserBirth());
            }
            if (userUpdateDto.getUserEmail() != null) {
                user.setUserEmail(userUpdateDto.getUserEmail());
            }
            if (userUpdateDto.getUserPhonenumber() != null) {
                user.setUserPhonenumber(userUpdateDto.getUserPhonenumber());
            }
            userRepository.save(user);
        }
        return user.toDto();
    }

    // 회원 가입
    public McUserDto saveUser(McUserDto userDto) {
        // 패스워드를 암호화하여 저장
        userDto.setUserPw(passwordEncoder.encode(userDto.getUserPw()));
        userDto.setUserJoindate(LocalDate.now());
        userDto.setUserSecession(false);

        return userRepository.save(userDto.toEntity()).toDto();
    }

    // 회원 탈퇴
    public McUserDto withdrawUser(String userId) {
        McUser user = userRepository.findById(userId).orElse(null);
        user.setUserSecession(true);
        return userRepository.save(user).toDto();
    }

    // 전체 회원 조회
    public List<McUserDto> listOfAllUsers() {
        List<McUser> users = userRepository.findAll();
        return users.stream().map(McUser::toDto).toList();
    }

    // 이용 회원 조회
    public List<McUserDto> listOfJoinUsers() {
        List<McUser> users = userRepository.listOfJoinUsers();
        return users.stream().map(McUser::toDto).toList();
    }

    // 탈퇴 회원 조회
    public List<McUserDto> listOfWithdrawUser() {
        List<McUser> users = userRepository.listOfWithdrawUsers();
        return users.stream().map(McUser::toDto).toList();
    }


}
