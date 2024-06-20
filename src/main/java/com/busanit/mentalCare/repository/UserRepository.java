package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 닉네임 찾기
    User findByUserNickname(String user_nickname);

}
