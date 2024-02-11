package com.beyondB.beyondB.converter;

import com.beyondB.beyondB.dto.response.BookResponseDTO;
import com.beyondB.beyondB.entity.Book;

import java.time.LocalDateTime;

public class BookConverter {
    public static BookResponseDTO.BookContentDTO toBookContentDTO(Book book) {
        return BookResponseDTO.BookContentDTO.builder()
                .bookId(book.getId())
                .title(book.getTitle())
                .bookSummary(book.getBookSummary())
                .bookImage(book.getBookImage())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publicationYear(book.getPublicationYear())
                .build();
    }

    public static BookResponseDTO.DetailBookDTO toDetailBookDTO(Book book, LocalDateTime quiz1Date, LocalDateTime quiz2Date, LocalDateTime quiz3Date) {
        return BookResponseDTO.DetailBookDTO.builder()
                .bookContent(BookResponseDTO.BookContentDTO.builder()
                        .bookId(book.getId())
                        .title(book.getTitle())
                        .bookSummary(book.getBookSummary())
                        .bookImage(book.getBookImage())
                        .author(book.getAuthor())
                        .publisher(book.getPublisher())
                        .publicationYear(book.getPublicationYear())
                        .build())
                .quiz1Date(quiz1Date)
                .quiz2Date(quiz2Date)
                .quiz3Date(quiz3Date)
                .build();
    }
}
