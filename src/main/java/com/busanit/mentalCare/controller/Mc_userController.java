package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.dto.Mc_userDto;
import com.busanit.mentalCare.model.Mc_user;
import com.busanit.mentalCare.service.CustomMc_userDetailsService;
import com.busanit.mentalCare.service.Mc_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Mc_userController {
    @Autowired  // 스프링 시큐리티 유저 서비스 DI
    UserDetailsService userDetailsService;

    @Autowired  // 일반 유저 서비스 DI
    Mc_userService userService;

    @Autowired  // 일반 유저 서비스 DI
    CustomMc_userDetailsService customUserService;

    @Autowired  // 인증 관리자 DI
    private AuthenticationManager authenticationManager;

    
    /* 메소드 */
    // 로그인
    @GetMapping("/login")
    public ResponseEntity<Mc_userDto> login(@RequestBody String user_id, @RequestBody String user_pw) {
        Mc_userDto userDto = userService.loginUser(user_id, user_pw);
        customUserService.loadUserByUsername(user_id);

        return ResponseEntity.ok(userDto);
    }

    // Id로 유저 찾기
    @GetMapping("/findUserByUserId")
    public ResponseEntity<Mc_userDto> findUserByUserId(@RequestBody String user_id) {
        Mc_userDto userDto = userService.findByUserId(user_id);
        return ResponseEntity.ok(userDto);
    }

    // 회원 정보 수정
    @PostMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody String user_id, @RequestBody Mc_user user) {
        userService.updateUser(user_id, user.toDto());
        return ResponseEntity.ok("회원 정보 수정 완료.");
    }

    // 회원 가입
    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody Mc_user user) {
        userService.saveUser(user);
        return ResponseEntity.ok("회원 가입 완료, 로그인 해주세요.");
    }

    // 회원 탈퇴
    @PostMapping("/withdrawUser")
    public ResponseEntity<String> withdrawUser(@RequestBody Mc_user user) {
        userService.withdrawUser(user);
        return ResponseEntity.ok("회원 탈퇴 완료, 다음에 또 만나요!");
    }

    // 전체 회원 조회
    @GetMapping("/AllUsers")
    public ResponseEntity<List<Mc_userDto>> listOfAllUsers() {
        List<Mc_userDto> allUserList = userService.listOfAllUsers();
        return ResponseEntity.ok(allUserList);
    }

    // 이용 회원 조회
    @GetMapping("/listOfWithdrawUsers")
    public ResponseEntity<List<Mc_userDto>> listOfJoinUsers() {
        List<Mc_userDto> joinUserList = userService.listOfJoinUsers();
        return ResponseEntity.ok(joinUserList);
    }

    // 탈퇴 회원 조회
    @GetMapping("/listOfWithdrawUsers")
    public ResponseEntity<List<Mc_userDto>> listOfWithdrawUsers() {
        List<Mc_userDto> withdrawUserList = userService.listOfWithdrawUser();
        return ResponseEntity.ok(withdrawUserList);
    }

}
