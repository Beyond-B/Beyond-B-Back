package com.beyondB.beyondB.service.impl;

import com.beyondB.beyondB.apiPayload.code.status.ErrorStatus;
import com.beyondB.beyondB.apiPayload.exception.BookException;
import com.beyondB.beyondB.apiPayload.exception.DiaryException;
import com.beyondB.beyondB.apiPayload.exception.UserBookException;
import com.beyondB.beyondB.converter.BookConverter;
import com.beyondB.beyondB.dto.request.BookRequestDTO;
import com.beyondB.beyondB.dto.response.BookResponseDTO;
import com.beyondB.beyondB.entity.*;
import com.beyondB.beyondB.entity.enums.Age;
import com.beyondB.beyondB.entity.enums.Emotion;
import com.beyondB.beyondB.entity.mapping.UserBook;
import com.beyondB.beyondB.repository.BookAgeRepository;
import com.beyondB.beyondB.repository.BookRepository;
import com.beyondB.beyondB.repository.FeelingRepository;
import com.beyondB.beyondB.repository.UserBookRepository;
import com.beyondB.beyondB.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final FeelingRepository feelingRepository;
    private final UserBookRepository userBookRepository;

    @Transactional
    public Book createBook(BookRequestDTO.CreateBookDTO request) {
        if (!isImageUrlValid(request.getBooKImage())) {
            throw new BookException(ErrorStatus.BOOK_BAD_REQUEST);
        }

        Feeling feeling = feelingRepository.findByEmotion(request.getEmotion());
        Book book = Book.builder()
                .title(request.getTitle())
                .bookSummary(request.getBookSummary())
                .bookImage(request.getBooKImage())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .publicationYear(request.getPublicationYear())
                .feeling(feeling)
                .build();

        List<BookAge> bookAgeList = new ArrayList<>();
        for (Age age : request.getAges()) {
            BookAge bookAge = BookAge.builder()
                    .age(age)
                    .book(book)
                    .build();
            bookAgeList.add(bookAge);
        }

        book.getBookAgeList().addAll(bookAgeList);

        bookRepository.save(book);
        return book;
    }
    @Override
    public BookResponseDTO.DetailBookDTO getDetailBook(User user, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException(ErrorStatus.BOOK_NOT_FOUND));

        UserBook userBook = userBookRepository.findByUserAndBook(user, book);
        if (userBook == null) {
            throw new UserBookException(ErrorStatus.USER_BOOK_NOT_FOUND);
        }

        LocalDateTime quiz1Date = userBook.getQuiz1Date();
        LocalDateTime quiz2Date = userBook.getQuiz2Date();
        LocalDateTime quiz3Date = userBook.getQuiz3Date();

        return BookConverter.toDetailBookDTO(book, quiz1Date, quiz2Date, quiz3Date);
    }

    private boolean isImageUrlValid(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            String contentType = connection.getContentType();
            return contentType != null && contentType.startsWith("image/");
        } catch (Exception e) {
            return false;
        }
    }
}
