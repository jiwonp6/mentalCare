package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.model.EmotionDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionDiaryRepository extends JpaRepository<EmotionDiary, Long> {
    // 오늘 감정일지
    @Query(value = "SELECT ed FROM EmotionDiary ed JOIN ed.mcUser u JOIN ed.emotion e WHERE u.userId = :userId AND date(ed.edDate) = current_date()")
    EmotionDiary todayEmotionDiary(@Param("userId") String userId);

    // 해당 날짜 감정일지
    @Query(value = "SELECT ed, e FROM EmotionDiary ed JOIN ed.mcUser u JOIN ed.emotion e WHERE u.userId = :userId AND ed.edDate = :edDate")
    EmotionDiary selectedDateEmotionDiary(@Param("userId") String userId, @Param("edDate") String edDate);

    // 감정일지 삭제
    @Modifying
    @Query(value = "DELETE FROM EmotionDiary ed WHERE ed.edDate = :edDate AND ed.mcUser.userId = :userId")
    int deleteEmotionDiary(@Param("userId") String userId, @Param("edDate") String edDate);

}
