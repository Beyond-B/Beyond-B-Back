package com.beyondB.beyondB.converter;

import com.beyondB.beyondB.dto.response.BookResponseDTO;
import com.beyondB.beyondB.entity.Book;
import com.beyondB.beyondB.entity.mapping.UserBook;
import java.util.List;
import java.util.stream.Collectors;

public class BookConverter {

    public static List<BookResponseDTO.BookPreviewDTO> toBookPreviewDTO(List<Book> books, List<UserBook> userBooks) {
        return books.stream().map(book -> {

            UserBook matchingUserBook = userBooks.stream()
                    .filter(userBook -> userBook.getBook().getId().equals(book.getId()))
                    .findFirst()
                    .orElse(null);

            // quizDate의 존재 여부에 따라 boolean 값을 설정합니다.
            boolean quiz1 = matchingUserBook != null && matchingUserBook.getQuiz1Date() != null;
            boolean quiz2 = matchingUserBook != null && matchingUserBook.getQuiz2Date() != null;
            boolean quiz3 = matchingUserBook != null && matchingUserBook.getQuiz3Date() != null;

            return BookResponseDTO.BookPreviewDTO.builder()
                    .bookId(book.getId())
                    .title(book.getTitle())
                    .quiz1(quiz1)
                    .quiz2(quiz2)
                    .quiz3(quiz3)
                    .build();
        }).collect(Collectors.toList());
    }

    public static BookResponseDTO.BookContentDTO toCreatBookDTO(Book book) {
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


}

