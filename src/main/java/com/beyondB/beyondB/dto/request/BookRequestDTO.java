package com.beyondB.beyondB.dto.request;

import com.beyondB.beyondB.entity.enums.Age;
import com.beyondB.beyondB.entity.enums.Emotion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class BookRequestDTO {
    @Getter
    @Setter
    public static class CreateBookDTO {
        private String title;
        private String bookSummary;
        private String image;
        private String author;
        private String publisher;
        private int publicationYear;
        private List<Age> ages;
        private Emotion emotion;
    }
}
