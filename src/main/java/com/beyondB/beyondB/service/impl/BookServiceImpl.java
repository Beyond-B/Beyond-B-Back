package com.beyondB.beyondB.service.impl;

import com.beyondB.beyondB.entity.Book;
import com.beyondB.beyondB.entity.Feeling;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.entity.enums.Emotion;
import com.beyondB.beyondB.entity.mapping.UserBook;
import com.beyondB.beyondB.repository.BookRepository;
import com.beyondB.beyondB.repository.FeelingRepository;
import com.beyondB.beyondB.repository.UserBookRepository;
import com.beyondB.beyondB.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final FeelingRepository feelingRepository;
    private final UserBookRepository userBookRepository;

    @Override
    public List<Book> getBookPreview(Emotion emotion, User user) {
        Feeling feeling = feelingRepository.findByEmotion(emotion);
        return bookRepository.findAllByFeeling(feeling);
    }

    public List<UserBook> getUserBooks(User user) {
        return userBookRepository.findAllByUser(user);
    }
}
