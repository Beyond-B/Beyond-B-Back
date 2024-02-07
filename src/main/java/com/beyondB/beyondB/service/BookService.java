package com.beyondB.beyondB.service;

import com.beyondB.beyondB.dto.request.BookRequestDTO;
import com.beyondB.beyondB.entity.Book;

public interface BookService {
    Book createBook(BookRequestDTO.CreateBookDTO request);
}
