package com.beyondB.beyondB.entity;

import com.beyondB.beyondB.entity.mapping.BookFeeling;
import com.beyondB.beyondB.entity.mapping.UserBook;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String bookSummary;

    private String author;

    private String publisher;

    private String publicationYear;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookAge> bookAgeList = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookQuiz> bookQuizList = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<UserBook> userBookList = new ArrayList<>();

    @OneToOne(mappedBy = "book")
    private BookFeeling bookFeeling;
}
