package com.beyondB.beyondB.converter;

import com.beyondB.beyondB.dto.response.BookResponseDTO;
import com.beyondB.beyondB.entity.Book;

public class BookConverter {
    public static BookResponseDTO.BookContentDTO toBookContentDTO(Book book) {
        return BookResponseDTO.BookContentDTO.builder()
                .bookId(book.getId())
                .title(book.getTitle())
                .bookSummary(book.getBookSummary())
                //이미지 가져오기 추가 필요
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publicationYear(book.getPublicationYear())
                .build();
    }
}
