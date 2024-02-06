package com.beyondB.beyondB.dto.response;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

public class DiaryResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public  static class DiaryResponseResultDTO{
        private Long diaryId;
        private LocalDate date;
        private String event;
        private String thought;
        private String emotionSpecific;
        private String behavior;
        private String result;
    }
}
