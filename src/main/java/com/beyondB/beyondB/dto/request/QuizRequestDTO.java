package com.beyondB.beyondB.dto.request;

import com.beyondB.beyondB.entity.enums.Age;
import com.beyondB.beyondB.entity.enums.Emotion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class QuizRequestDTO {
    @Getter
    @Setter
    public static class SubmitQuizDTO {
        private Long bookId;
        private Integer step;
    }
}
