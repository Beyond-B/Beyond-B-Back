package com.beyondB.beyondB.converter;

import com.beyondB.beyondB.dto.response.DiaryResponseDTO;
import com.beyondB.beyondB.entity.Diary;

import java.util.Date;

public class DiaryConverter {
    public static DiaryResponseDTO.DiaryContentDTO toDiaryContentDTO(Diary diary) {
        return DiaryResponseDTO.DiaryContentDTO.builder()
                .diaryId(diary.getId())
                .date(diary.getDate())
                .event(diary.getEvent())
                .thought(diary.getThought())
                .emotionSpecific(diary.getEmotionSpecific())
                .behavior(diary.getBehavior())
                .result(diary.getResult())
                .build();
    }
}
