package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.model.Board;
import com.busanit.mentalCare.model.Heart;
import com.busanit.mentalCare.model.McUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {

    // 공감이 있는지 없는지 검토
    boolean existsByUserAndBoard(@Param("user") McUser user,
                                 @Param("board") Board board);

    //삭제
    void deleteByUserAndBoard(@Param("user") McUser user,
                              @Param("board") Board board);
}