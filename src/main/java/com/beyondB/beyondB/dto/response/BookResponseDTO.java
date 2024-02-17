package com.beyondB.beyondB.dto.response;

import com.beyondB.beyondB.entity.enums.Emotion;
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
    public static class BookPreviewDTO {
        private Long bookId;
        private String title;
        private Boolean quiz1;
        private Boolean quiz2;
        private Boolean quiz3;
    }

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
        private LocalDateTime recommendationDate;
        private Emotion emotion;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecentBookDTO {
        private Long bookId;
    }
}
