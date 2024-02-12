package com.beyondB.beyondB.repository;

import com.beyondB.beyondB.entity.Book;
import com.beyondB.beyondB.entity.Feeling;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByFeeling(Feeling feeling);
}
