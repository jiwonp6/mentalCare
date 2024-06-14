package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.model.Board;
import com.busanit.mentalCare.model.Heart;
import com.busanit.mentalCare.model.Mc_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByUserAndContent(Mc_user user, Board board);
}
