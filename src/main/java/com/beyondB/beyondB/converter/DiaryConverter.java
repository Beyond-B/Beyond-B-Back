package com.beyondB.beyondB.converter;

import com.beyondB.beyondB.dto.response.DiaryResponseDTO;
import com.beyondB.beyondB.entity.Diary;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
                .feeling(diary.getFeeling().getEmotion())
                .build();
    }
    public static DiaryResponseDTO.MonthlyDiaryDTO toMonthlyDiaryDTO(
            List<Diary> diaries){
        List<DiaryResponseDTO.MonthlyDiarySummaryDTO> diarySummaries = diaries.stream()
                .map(DiaryConverter::toMonthlyDiarySummaryDTO)
                .collect(Collectors.toList());

        return DiaryResponseDTO.MonthlyDiaryDTO.builder()
                .diarySummaries(diarySummaries)
                .build();
    }
    public static DiaryResponseDTO.MonthlyDiarySummaryDTO toMonthlyDiarySummaryDTO(Diary diary) {
        return DiaryResponseDTO.MonthlyDiarySummaryDTO.builder()
                .diaryId(diary.getId())
                .date(diary.getDate())
                .feeling(diary.getFeeling().getEmotion())
                .build();
    }
}
