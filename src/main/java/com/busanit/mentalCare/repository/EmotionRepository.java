package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.model.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionRepository extends JpaRepository<Emotion, Long> {
    // 이모션 삭제
    @Query("DELETE FROM Emotion e WHERE e.emotionId = :emotionId")
    int deleteEmotion(@Param("emotionId") Long emotionId);
}
