package com.beyondB.beyondB.dto.response;

import com.beyondB.beyondB.entity.enums.Emotion;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

public class DiaryResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiaryContentDTO{
        private Long diaryId;
        private LocalDate date;
        private String event;
        private String thought;
        private String emotionSpecific;
        private String behavior;
        private String result;
        private Emotion feeling;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MonthlyDiaryDTO{
        private List<MonthlyDiarySummaryDTO> diarySummaries;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MonthlyDiarySummaryDTO{
        private Long diaryId;
        private LocalDate date;
        private Emotion feeling;
    }
}
