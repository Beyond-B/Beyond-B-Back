package com.beyondB.beyondB.service.impl;

import com.beyondB.beyondB.apiPayload.code.status.ErrorStatus;
import com.beyondB.beyondB.apiPayload.exception.BookException;
import com.beyondB.beyondB.apiPayload.exception.UserBookException;
import com.beyondB.beyondB.converter.BookConverter;
import com.beyondB.beyondB.dto.request.BookRequestDTO;
import com.beyondB.beyondB.dto.response.BookResponseDTO;
import com.beyondB.beyondB.entity.enums.Age;
import com.beyondB.beyondB.entity.enums.Emotion;
import com.beyondB.beyondB.entity.mapping.UserBook;
import com.beyondB.beyondB.entity.Book;
import com.beyondB.beyondB.entity.BookAge;
import com.beyondB.beyondB.entity.Feeling;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.repository.BookRepository;
import com.beyondB.beyondB.repository.FeelingRepository;
import com.beyondB.beyondB.repository.UserBookRepository;
import com.beyondB.beyondB.service.BookService;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final FeelingRepository feelingRepository;
    private final UserBookRepository userBookRepository;

    @Override
    public List<Book> getBookPreview(Emotion emotion, User user) {
        Feeling feeling = feelingRepository.findByEmotion(emotion);
        return bookRepository.findAllByFeeling(feeling);
    }

    public List<UserBook> getUserBooks(User user) {
        return userBookRepository.findAllByUser(user);

    }

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

    @Override
    public Book recommendBook(Emotion emotion, Age age, User user) {
        Feeling feeling = feelingRepository.findByEmotion(emotion);
        List<Book> books = bookRepository.findAllByFeeling(feeling);
        if (books.isEmpty()) {
            throw new BookException(ErrorStatus.BOOK_NOT_FOUND);
        }

        List<Long> readBookIds = user.getUserBookList().stream()
                .map(userBook -> userBook.getBook().getId())
                .collect(Collectors.toList());

        Book recommendedBook = findBookByAge(books, age, readBookIds);
        if (recommendedBook != null) {
            return recommendedBook;
        }

        // 연령대 범위를 확장해 나가며 책 검색하기
        for (int i = 1; recommendedBook == null && i < Age.values().length; i++) {
            Age lowerAge = age.getLower(i);
            if (lowerAge != null) {
                recommendedBook = findBookByAge(books, lowerAge, readBookIds);
            }

            if (recommendedBook == null) {
                Age higherAge = age.getHigher(i);
                if (higherAge != null) {
                    recommendedBook = findBookByAge(books, higherAge, readBookIds);
                }
            }
        }

        if (recommendedBook == null) {
            throw new BookException(ErrorStatus.BOOK_EMOTION_NOT_EXIST);
        }
        return recommendedBook;
    }

    private Book findBookByAge(List<Book> books, Age age, List<Long> readBookIds) {
        for (Book book : books) {
            if (readBookIds.contains(book.getId())) {
                continue;
            }
            for (BookAge bookAge : book.getBookAgeList()) {
                if (bookAge.getAge().equals(age)) {
                    return book;
                }
            }
        }
        return null;
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
