package com.beyondB.beyondB.service;

import com.beyondB.beyondB.dto.request.BookRequestDTO;
import com.beyondB.beyondB.dto.response.BookResponseDTO;
import com.beyondB.beyondB.entity.Book;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.entity.enums.Emotion;
import com.beyondB.beyondB.entity.mapping.UserBook;
import java.util.List;

public interface BookService {
    List<Book> getBookPreview(Emotion emotion, User user);

    List<UserBook> getUserBooks(User user);

    Book createBook(BookRequestDTO.CreateBookDTO request);
    BookResponseDTO.DetailBookDTO getDetailBook(User user, Long bookId);
}
