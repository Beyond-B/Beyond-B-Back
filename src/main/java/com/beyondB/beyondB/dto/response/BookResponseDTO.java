package com.beyondB.beyondB.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BookResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public  static class BookContentDTO{
        private Long bookId;
        private String title;
        private String bookSummary;
        private String bookImage;
        private String author;
        private String publisher;
        private int publicationYear;
    }
}
