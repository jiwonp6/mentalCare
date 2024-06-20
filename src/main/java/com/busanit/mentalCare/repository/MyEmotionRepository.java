package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.model.Emotion;
import com.busanit.mentalCare.model.MyEmotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MyEmotionRepository extends JpaRepository<Emotion, Long> {
    // 내 이모션 id로 찾기
    @Query("SELECT me FROM MyEmotion me WHERE me.myEmotionId = :myEmotionId")
    MyEmotion findByMyEmotionId(@Param("myEmotionId") Long myEmotionId);

    // 내 이모션 추가
    @Query("INSERT INTO MyEmotion me (userId, emotionId, myEmotionReason, myEmotionDate) VALUES (:userId, :emotionId, :myEmotionReason, :myEmotionDate)")
    int createMyEmotion(MyEmotion myEmotion);

    // 내 이모션 수정
    @Query("UPDATE MyEmotion me SET me.emotionId = :emotionId, me.myEmotionReason = :myEmotionReason, me.myEmotionDate = :myEmotionDate")
    int updateMyEmotion(MyEmotion myEmotion);


    // 내 이모션 삭제
    @Query("DELETE FROM MyEmotion me WHERE me.myEmotionId = :myEmotionId")
    int deleteMyEmotion(@Param("myEmotionId") Long myEmotionId);

    // 날짜별 내 이모션 리스트
    @Query("SELECT me FROM MyEmotion me WHERE me.myEmotionDate = :myEmotionDate")
    List<MyEmotion> listOfMyEmotionListByDate(@Param("myEmotionDate") Date myEmotionDate);
}
