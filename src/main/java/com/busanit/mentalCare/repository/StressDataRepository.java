package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.model.StressData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StressDataRepository extends JpaRepository<StressData, String> {
    // 오늘 데이터 불러오기
    @Query(value = "SELECT std FROM StressData std JOIN std.mcUser u WHERE u.userId = :userId AND date(std.stdDate) = current_date")
    StressData todayStressData(@Param("userId") String userId);

    // 해당 날짜 데이터 불러오기
    @Query(value = "SELECT std FROM StressData std JOIN std.mcUser u WHERE u.userId = :userId AND std.stdDate = :stdDate")
    StressData selectedDateStressData(@Param("userId") String userId, @Param("stdDate") String stdDate);

}
