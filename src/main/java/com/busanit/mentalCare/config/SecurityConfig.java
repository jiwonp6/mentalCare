package com.busanit.mentalCare.config;

import com.busanit.mentalCare.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity // 메서드 보안 황성화
@EnableWebSecurity   // 웹 보안 활성화 선언
@Configuration       // 스프링 설정 클래스임을 선언
public class SecurityConfig {

    @Autowired  // JWT 요청 필터 의존성 주입
    private JwtRequestFilter jwtRequestFilter;

    @Autowired  // 의존성 주입 (userDetailsService 타입의 컴포넌트 => UserService)
    private UserDetailsService userDetailsService;

    // AuthenticationManager 인증 관리자
    @Bean   // 스프링 빈으로 등록할 경우 사용자 인증을 처리
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    // 평문 비밀번호를 암호화하여 DB에 저장 (암호화 방법 : BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean      // 스프링 컨테이너가 관리하는 Bean 객체임을 선언하는 애노테이션
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 비활성화
                .csrf(csrf -> csrf.disable())
                // HTTP 요청에 대한 권한 설정
                .authorizeRequests(auth -> auth
                        // 해당 패턴에 대해서는 권한 허용
                        .requestMatchers("/test", "/register", "/auth", "/jwt/**",
                                // SpringDoc Open API 주소 허용
                                "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()

                        // 그 외 모든 요청에 대해서 인증이 필요함
                        .anyRequest().authenticated()
                )
                // REST API는 무상태성을 가지기 때문에 세션을 무상태(STATELESS)로 설정.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // JWT 필터를 인증 필터 앞에 추가
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();

    }
}
/*
                // CSRF 보호 비활성화 (REST API 요청으로 비활성화)
                // CSRF (웹 보안 공격, 사용자가 요청할 때 인지 못한 상태에서 원하지 않는 액션을 수행하게 하는 공격)
                // CSRF 보호가 되어있는 웹에 접근하기 위해서는 CSRF 토큰이 필요하고 검증



//                .formLogin(form -> form
//                        .loginPage("/login")    // 로그인 페이지의 경로
//                        .permitAll()            // 로그인 페이지는 모두에게 허용
//                )
//                .logout(logout -> logout
//                        .permitAll());
        // 로그아웃도 모두에게 허용
 */