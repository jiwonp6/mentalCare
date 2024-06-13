package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.model.Emotion;
import com.busanit.mentalCare.model.My_emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface My_emotionRepository extends JpaRepository<Emotion, Long> {
    // 내 이모션 id로 찾기
    @Query("SELECT me FROM my_emotion me WHERE me.my_emotion_id LIKE :my_emotion_id")
    My_emotion findByMyEmotionId(int my_emotion_id);

    // 내 이모션 추가
    @Query("INSERT INTO My_emotion me (user_id, emotion_id, my_emotion_reason, my_emotion_date) VALUES (:user_id, :emotion_id, :my_emotion_reason, :my_emotion_date)")
    int createMyEmotion(My_emotion my_emotion);

    // 내 이모션 수정
    @Query("UPDATE My_emotion me SET me.emotion_id = :emotion_id, me.my_emotion_reason = :my_emotion_reason, me.my_emotion_date = :my_emotion_date")
    int updateMyEmotion(My_emotion my_emotion);


    // 내 이모션 삭제
    @Query("DELETE FROM My_emotion me WHERE me.my_emotion_id = :my_emotion_id")
    int deleteMyEmotion(int my_emotion_id);

    // 날짜별 내 이모션 리스트
    @Query("SELECT me FROM My_emotion me WHERE me.my_emotion_date = :my_emotion_date")
    List<My_emotion> listOfMyEmotionListByDate(Date my_emotion_date);
}
