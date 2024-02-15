package com.beyondB.beyondB.controller;

import com.beyondB.beyondB.apiPayload.BaseResponse;
import com.beyondB.beyondB.converter.UserConverter;
import com.beyondB.beyondB.dto.UserSignupDTO;
import com.beyondB.beyondB.dto.response.UserResponseDTO.UserDetailDTO;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.security.handler.annotation.AuthUser;
import com.beyondB.beyondB.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserSignupDTO userSignUpDto) throws Exception {
        userService.signUp(userSignUpDto);
        return "회원가입 성공";
    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }

    @GetMapping("/test")
    public String test() {
        return "test 요청 성공";
    }

    @GetMapping("/mypage")
    @Parameter(name = "user", hidden = true)
    public BaseResponse<UserDetailDTO> myPage(@AuthUser User user) {
        return BaseResponse.onSuccess(UserConverter.toUserDetailDTO(user));
    }
}
