package com.busanit.mentalCare.service;

import com.busanit.mentalCare.model.McUser;
import com.busanit.mentalCare.repository.McUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 스프링 시큐리티에서 사용자 정보를 로드하기 위한 클래스
@Service
public class CustomMcUserDetailsService implements UserDetailsService {

    @Autowired     // 레포지토리 의존성 주입
    private McUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // username으로 DB 조회 -> User객체
        McUser user = userRepository.findById(userId).orElse(null);

        // 사용자가 없는 경우 예외발생
        if (user == null) {
            throw new UsernameNotFoundException("회원을 찾을 수 없습니다.");
        }

        // UserDetails 객체(스프링 시큐리티가 관리) 로 변환
        UserDetails userDetail = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserId())
                .password(user.getUserPw())
                .roles("USER")  // 일반 사용자 역할
                .build();
        return userDetail;
    }
}
