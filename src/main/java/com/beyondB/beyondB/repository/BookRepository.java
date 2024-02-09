package com.beyondB.beyondB.repository;

import com.beyondB.beyondB.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
