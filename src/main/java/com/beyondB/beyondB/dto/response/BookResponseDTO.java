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
    public static class BookPreviewDTO {
        private Long bookId;
        private String title;

        private Boolean quiz1;
        private Boolean quiz2;
        private Boolean quiz3;
    }

}
