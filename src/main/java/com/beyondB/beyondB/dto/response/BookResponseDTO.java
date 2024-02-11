package com.beyondB.beyondB.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
        private Integer publicationYear;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailBookDTO {
        private BookResponseDTO.BookContentDTO bookContent;
        private LocalDateTime quiz1Date;
        private LocalDateTime quiz2Date;
        private LocalDateTime quiz3Date;
    }
}
