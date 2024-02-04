package com.beyondB.beyondB.security.test;

import com.beyondB.beyondB.apiPayload.BaseResponse;
import com.beyondB.beyondB.security.test.dto.LoginRequest;
import com.beyondB.beyondB.security.test.dto.LoginResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Test Auth 🧪🔐", description = "인증/인가 테스트 용")
public class TestAuthController {

    private final TestAuthService authService;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        return BaseResponse.onSuccess(authService.login(request.getEmail()));
    }
}
