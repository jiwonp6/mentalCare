package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.dto.McUserDto;
import com.busanit.mentalCare.dto.McUserLoginSuccessDto;
import com.busanit.mentalCare.dto.McUserUpdateDto;
import com.busanit.mentalCare.jwt.JwtUtil;
import com.busanit.mentalCare.service.CustomMcUserDetailsService;
import com.busanit.mentalCare.service.McUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/mcUser")
@RestController
public class McUserController {
    @Autowired  // 스프링 시큐리티 유저 서비스 DI
    UserDetailsService userDetailsService;

    @Autowired  // 일반 유저 서비스 DI
    McUserService userService;

    @Autowired  // 일반 유저 서비스 DI
    CustomMcUserDetailsService customUserService;

    @Autowired  // 인증 관리자 DI
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    /* 메소드 */
    // 로그인
    @PostMapping("/authLogin")
    public McUserLoginSuccessDto authToken(@RequestBody McUserDto userDto) throws Exception {
        // 스프링 시큐리티 인증관리자로 사용자로부터 받은 정보로 인증 토큰을 생성하여 인증
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDto.getUserId(),
                            userDto.getUserPw()
                    )
            );
        } catch (BadCredentialsException e) {
            // 인증 실패되는 경우
            System.out.println("인증 실패");
            throw new Exception("fail to auth");
        }
        // 인증된 사용자 정보를 가져옴
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUserId());
        McUserDto byUserId = userService.findByUserId(userDto.getUserId());

        // 토큰 생성하기 (UserDetails 정보 기반)
        String jwt = jwtUtil.generateToken(userDetails);

        // 토큰 정보를 담은 McUserLoginDto 객체 반환
        McUserLoginSuccessDto mcUserLogin
                = new McUserLoginSuccessDto(
                        userDto.getUserId(),
                        byUserId.getUserPw(),
                        byUserId.getUserNickname(),
                        byUserId.getUserGender(),
                        byUserId.getUserBirth(),
                        byUserId.getUserEmail(),
                        byUserId.getUserPhonenumber(),
                        byUserId.isUserSecession(), jwt
                );

        return mcUserLogin;
    }

    // Id로 유저 찾기
    @GetMapping("/findByUserId")
    public ResponseEntity<McUserDto> findByUserId(@RequestParam String userId) {
        McUserDto userDto = userService.findByUserId(userId);
        return ResponseEntity.ok(userDto);
    }

    // 회원 정보 수정
    @PostMapping("/updateUser")
    public ResponseEntity<McUserDto> updateUser(@RequestParam String userId, @RequestBody McUserUpdateDto userUpdateDto) {
        McUserDto userDto = userService.updateUser(userId, userUpdateDto);
        return ResponseEntity.ok(userDto);
    }

    // 회원 가입
    @PostMapping("/createUser")
    public ResponseEntity<McUserDto> createUser(@RequestBody McUserDto userDto) {
        System.out.println(userDto);
        
        McUserDto mcUserDto = userService.saveUser(userDto);

        return ResponseEntity.ok(mcUserDto);
    }

    // 회원 탈퇴
    @PostMapping("/withdrawUser")
    public ResponseEntity<Boolean> withdrawUser(@RequestParam String userId, @RequestBody McUserDto checkUserDto) throws Exception {
        Boolean result = userService.withdrawUser(userId, checkUserDto);
        return ResponseEntity.ok(result);
    }

    // 전체 회원 조회
    @GetMapping("/AllUsers")
    public ResponseEntity<List<McUserDto>> listOfAllUsers() {
        List<McUserDto> allUserList = userService.listOfAllUsers();
        return ResponseEntity.ok(allUserList);
    }

    // 이용 회원 조회
    @GetMapping("/listOfJoinUsers")
    public ResponseEntity<List<McUserDto>> listOfJoinUsers() {
        List<McUserDto> joinUserList = userService.listOfJoinUsers();
        return ResponseEntity.ok(joinUserList);
    }

    // 탈퇴 회원 조회
    @GetMapping("/listOfWithdrawUsers")
    public ResponseEntity<List<McUserDto>> listOfWithdrawUsers() {
        List<McUserDto> withdrawUserList = userService.listOfWithdrawUser();
        return ResponseEntity.ok(withdrawUserList);
    }

}
