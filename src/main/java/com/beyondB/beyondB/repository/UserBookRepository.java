package com.beyondB.beyondB.repository;

import com.beyondB.beyondB.entity.Book;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.entity.mapping.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {
    UserBook findByUserAndBook(User user, Book book);
    List<UserBook> findAllByUser(User user);
}