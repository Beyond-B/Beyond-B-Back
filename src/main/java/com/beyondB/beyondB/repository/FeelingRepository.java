package com.beyondB.beyondB.repository;

import com.beyondB.beyondB.entity.Feeling;
import com.beyondB.beyondB.entity.enums.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeelingRepository extends JpaRepository<Feeling, Long> {

    Feeling findByEmotion(Emotion emotion);
}
