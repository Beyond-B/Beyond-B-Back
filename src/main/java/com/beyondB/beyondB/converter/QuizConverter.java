package com.beyondB.beyondB.converter;

import com.beyondB.beyondB.dto.response.QuizResponseDTO;
import com.beyondB.beyondB.entity.BookQuiz;

public class QuizConverter {
    public static QuizResponseDTO.QuizDetailDTO toQuizDetailDTO(BookQuiz bookQuiz) {
        return QuizResponseDTO.QuizDetailDTO.builder()
                .quizId(bookQuiz.getId())
                .bookId(bookQuiz.getBook().getId())
                .question(bookQuiz.getQuestion())
                .option1(bookQuiz.getOption1())
                .option2(bookQuiz.getOption2())
                .option3(bookQuiz.getOption3())
                .option4(bookQuiz.getOption4())
                .answerOption(bookQuiz.getAnswerOption())
                .step(bookQuiz.getStep())
                .build();
    }
}
