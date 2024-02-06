package com.beyondB.beyondB.converter;

import com.beyondB.beyondB.dto.response.DiaryResponseDTO;
import com.beyondB.beyondB.entity.Diary;

import java.util.Date;

public class DiaryConverter {
    public static DiaryResponseDTO.DiaryResponseResultDTO toUpdateDiaryDTO(Diary diary) {
        return DiaryResponseDTO.DiaryResponseResultDTO.builder()
                .diary_id(diary.getId())
//                .date(diary.getDate())
//                날짜는 수정 불가능 한가요..? -> 이에 따라 DTO 수정필요
                .event(diary.getEvent())
                .thought(diary.getThought())
                .emotionSpecific(diary.getEmotionSpecific())
                .behavior(diary.getBehavior())
                .result(diary.getResult())


                .build();
    }
}
