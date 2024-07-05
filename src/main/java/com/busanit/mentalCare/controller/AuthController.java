package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.dto.McUserDto;
import com.busanit.mentalCare.jwt.JwtUtil;
import com.busanit.mentalCare.model.McUser;
import com.busanit.mentalCare.repository.McUserRepository;
import com.busanit.mentalCare.service.CustomMcUserDetailsService;
import com.busanit.mentalCare.service.McUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/jwt")
@RequiredArgsConstructor
public class AuthController {

    // 스프링 컴포넌트 의존성 주입
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final McUserService userService;
    private final CustomMcUserDetailsService customUserService;

    @PostMapping("/auth")
    public Map<String, String> authToken(@RequestBody McUser userDto) throws Exception {
        try {
            // 인증 관리자로 인증
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDto.getUserId(),
                            userDto.getUserPw()
                    )
            );
        } catch (BadCredentialsException e) {   // 인증 실패시
            throw new Exception("wrong UserId or Password", e);
        }

        UserDetails userDetails = customUserService.loadUserByUsername(userDto.getUserId());

        String jwt = jwtUtil.generateToken(userDetails);

        Map<String, String> response = new HashMap<>();
        response.put("jwt", jwt);

        return response;
    }

    @PostMapping("/createUser")
    public McUserDto registerUser(@RequestBody McUserDto userDto) {
        return userService.saveUser(userDto);
    }
}
