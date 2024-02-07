package com.beyondB.beyondB.dto.request;

import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.entity.enums.Emotion;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.time.YearMonth;
import java.util.Date;

public class DiaryRequestDTO {

    @Getter
    @Setter
    public static class UpdateDiaryDTO {
        @NotNull
        private Long diaryId;
        private LocalDate date;
        private String event;
        private String thought;
        private String emotionSpecific;
        private String behavior;
        private String result;
    }
    @Getter
    @Setter
    public static class CreateDiaryDTO {
        private LocalDate date;
        private String event;
        private String thought;
        private String emotionSpecific;
        private String behavior;
        private String result;
        private Emotion emotion;
    }
}