package com.beyondB.beyondB.repository;

import com.beyondB.beyondB.entity.Book;
import com.beyondB.beyondB.entity.BookAge;
import com.beyondB.beyondB.entity.enums.Age;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookAgeRepository extends JpaRepository<BookAge, Long> {
}
