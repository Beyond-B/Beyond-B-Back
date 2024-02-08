package com.beyondB.beyondB.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSignupDTO {

    private String email;
    private String password;
    private String username;
    private String age;
}
