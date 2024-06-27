package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.model.SleepData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SleepDataRepository extends JpaRepository<SleepData, String> {
    // 해당 날짜 데이터 불러오기
    @Query(value = "SELECT sld FROM SleepData sld JOIN sld.mcUser u WHERE u.userId = :userId AND sld.sldDate = :sldDate")
    SleepData selectedDateSleepData(@Param("userId") String userId, @Param("sldDate") String sldDate);

}
