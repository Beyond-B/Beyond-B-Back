package com.beyondB.beyondB.converter;

import com.beyondB.beyondB.dto.response.QuizResponseDTO;
import com.beyondB.beyondB.entity.BookQuiz;

public class QuizConverter {
    public static QuizResponseDTO.GetQuizDTO toGetQuizDTO(BookQuiz bookQuiz) {
        return QuizResponseDTO.GetQuizDTO.builder()
                .quizId(bookQuiz.getId())
                .question(bookQuiz.getQuestion())
                .option1(bookQuiz.getOption1())
                .option2(bookQuiz.getOption2())
                .option3(bookQuiz.getOption3())
                .option4(bookQuiz.getOption4())
                .answerOption(bookQuiz.getAnswerOption())
                .build();
    }
}
