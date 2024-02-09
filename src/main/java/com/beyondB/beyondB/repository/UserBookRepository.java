package com.beyondB.beyondB.repository;

import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.entity.mapping.UserBook;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {

    List<UserBook> findAllByUser(User user);
}
