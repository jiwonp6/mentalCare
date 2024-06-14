package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.model.Mc_user;
import com.busanit.mentalCare.service.Mc_userService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Mc_userRepository extends JpaRepository<Mc_user, Long> {
    // 로그인
    @Query("SELECT mc_u FROM Mc_user mc_u WHERE mc_u.user_id = :user_id AND mc_u.user_pw = :user_pw AND mc_u.user_secession = false")
    Mc_user loginUser(String user_id, String user_pw);

    // Id로 유저 찾기
    @Query("SELECT mc_u FROM Mc_user mc_u WHERE mc_u.user_id = :user_id")
    Mc_user findByUserId(String user_id);

    // 이용 회원 조회
    @Query("SELECT mc_u FROM Mc_user mc_u WHERE mc_u.user_secession = false")
    List<Mc_user> listOfJoinUsers();

    // 탈퇴 회원 조회
    @Query("SELECT mc_u FROM Mc_user mc_u WHERE mc_u.user_secession = true")
    List<Mc_user> listOfWithdrawUsers();

}
