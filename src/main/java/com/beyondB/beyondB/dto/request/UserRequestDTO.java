package com.beyondB.beyondB.dto.request;

import lombok.Getter;
import lombok.Setter;

public class UserRequestDTO {

    @Getter
    @Setter
    public static class PatchAgeDTO{
        private String age;
    }
}
