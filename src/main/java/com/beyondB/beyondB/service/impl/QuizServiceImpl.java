package com.beyondB.beyondB.service.impl;

import com.beyondB.beyondB.apiPayload.code.status.ErrorStatus;
import com.beyondB.beyondB.apiPayload.exception.BookException;
import com.beyondB.beyondB.apiPayload.exception.BookQuizException;
import com.beyondB.beyondB.apiPayload.exception.UserBookException;
import com.beyondB.beyondB.entity.Book;
import com.beyondB.beyondB.entity.BookQuiz;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.entity.mapping.UserBook;
import com.beyondB.beyondB.repository.BookRepository;
import com.beyondB.beyondB.repository.UserBookRepository;
import com.beyondB.beyondB.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final BookRepository bookRepository;
    private final UserBookRepository userBookRepository;

    public BookQuiz getQuiz(User user, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException(ErrorStatus.BOOK_NOT_FOUND));

        UserBook userBook = userBookRepository.findByUserAndBook(user, book);
        if (userBook == null) {
            throw new UserBookException(ErrorStatus.USER_BOOK_NOT_FOUND);
        }

        if (userBook.getQuiz1Date() == null) {
            return findQuizByStep(userBook, 1);
        } else if (userBook.getQuiz2Date() == null) {
            return findQuizByStep(userBook, 2);
        } else if (userBook.getQuiz3Date() == null) {
            return findQuizByStep(userBook, 3);
        } else {
            throw new BookQuizException(ErrorStatus.QUIZ_STEP_NOT_FOUND);
        }
    }
    private BookQuiz findQuizByStep(UserBook userBook, int step) {
        Book book = userBook.getBook();

        List<BookQuiz> quizzes = book.getBookQuizList();

        for (BookQuiz quiz : quizzes) {
            if (quiz.getStep() == step) {
                return quiz;
            }
        }
        throw new BookQuizException(ErrorStatus.QUIZ_NOT_FOUND);
    }
}
