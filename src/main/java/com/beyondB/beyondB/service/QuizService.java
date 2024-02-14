package com.beyondB.beyondB.service;

import com.beyondB.beyondB.dto.request.QuizRequestDTO;
import com.beyondB.beyondB.entity.BookQuiz;
import com.beyondB.beyondB.entity.User;

public interface QuizService {
    BookQuiz getQuiz(User user, Long bookId);
    void submitQuiz(User user, QuizRequestDTO.SubmitQuizDTO request);
}
