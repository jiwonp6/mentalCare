package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.model.McUser;
import com.busanit.mentalCare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface McUserRepository extends JpaRepository<McUser, String> {
    // 로그인
    @Query("SELECT mc FROM McUser mc WHERE mc.userId = :userId AND mc.userPw = :userPw AND mc.userSecession = false")
    McUser loginUser(@Param("userId") String userId, @Param("userPw") String userPw);

    // 이용 회원 조회
    @Query("SELECT mc FROM McUser mc WHERE mc.userSecession = false")
    List<McUser> listOfJoinUsers();

    // 탈퇴 회원 조회
    @Query("SELECT mc FROM McUser mc WHERE mc.userSecession = true")
    List<McUser> listOfWithdrawUsers();

    // 게시글 -> userNickname으로 게시글 조회
    McUser findByUserNickname(String userNickname);

    McUser findByUserId(String userId);

}
