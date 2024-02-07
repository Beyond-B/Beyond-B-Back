package com.beyondB.beyondB.service.impl;

import com.beyondB.beyondB.dto.request.BookRequestDTO;
import com.beyondB.beyondB.entity.Book;
import com.beyondB.beyondB.entity.BookAge;
import com.beyondB.beyondB.entity.Feeling;
import com.beyondB.beyondB.entity.enums.Age;
import com.beyondB.beyondB.entity.enums.Emotion;
import com.beyondB.beyondB.repository.BookAgeRepository;
import com.beyondB.beyondB.repository.BookRepository;
import com.beyondB.beyondB.repository.FeelingRepository;
import com.beyondB.beyondB.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final FeelingRepository feelingRepository;
    @Transactional
    public Book createBook(BookRequestDTO.CreateBookDTO request) {
        Book book = Book.builder()
                .title(request.getTitle())
                .bookSummary(request.getBookSummary())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .publicationYear(request.getPublicationYear())

                .build();

        List<BookAge> bookAgeList = new ArrayList<>();
        for (Age age : request.getAges()) {
            BookAge bookAge = BookAge.builder()
                    .age(age)
                    .book(book)
                    .build();
            bookAgeList.add(bookAge);
        }

        book.setBookAgeList(bookAgeList);

        Emotion emotion = request.getEmotion();
        Feeling feeling = feelingRepository.findByEmotion(emotion);

        book.setFeeling(feeling);

        bookRepository.save(book);
        return book;
    }
}
