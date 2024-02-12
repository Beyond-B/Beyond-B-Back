package com.beyondB.beyondB.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class QuizResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuizDetailDTO {
        private Long quizId;
        private String question;
        private String option1;
        private String option2;
        private String option3;
        private String option4;
        private int answerOption;
    }
}
