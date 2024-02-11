package com.beyondB.beyondB.service;

import com.beyondB.beyondB.dto.request.BookRequestDTO;
import com.beyondB.beyondB.dto.response.BookResponseDTO;
import com.beyondB.beyondB.entity.Book;
import com.beyondB.beyondB.entity.User;

public interface BookService {
    Book createBook(BookRequestDTO.CreateBookDTO request);
    BookResponseDTO.DetailBookDTO getDetailBook(User user, Long bookId);
}
