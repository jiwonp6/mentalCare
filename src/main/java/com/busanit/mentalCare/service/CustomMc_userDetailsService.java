package com.busanit.mentalCare.service;

import com.busanit.mentalCare.model.Mc_user;
import com.busanit.mentalCare.repository.Mc_userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 스프링 시큐리티에서 사용자 정보를 로드하기 위한 클래스
@Service
public class CustomMc_userDetailsService implements UserDetailsService {

    @Autowired     // 레포지토리 의존성 주입
    private Mc_userRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        // username으로 DB 조회 -> User객체
        Mc_user user = userRepository.findByUserId(user_id);

        // 사용자가 없는 경우 예외발생
        if (user == null) {
            throw new UsernameNotFoundException("회원을 찾을 수 없습니다.");
        }

        // UserDetails 객체(스프링 시큐리티가 관리) 로 변환
        UserDetails userDetail = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUser_id())
                .password(user.getUser_pw())
                .roles("USER")  // 일반 사용자 역할
                .build();
        return userDetail;
    }
}
