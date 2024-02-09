package com.beyondB.beyondB.repository;

import com.beyondB.beyondB.entity.Diary;
import com.beyondB.beyondB.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    @Query("SELECT d FROM Diary d WHERE d.user = :user AND FUNCTION('YEAR', d.date) = :year AND FUNCTION('MONTH', d.date) = :month")
    List<Diary> findAllByUserAndYearAndMonth(User user, int year, int month);
}
