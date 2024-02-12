package com.beyondB.beyondB.controller;

import com.beyondB.beyondB.apiPayload.BaseResponse;
import com.beyondB.beyondB.dto.request.LoginRequestDTO;
import com.beyondB.beyondB.dto.response.AuthResponseDTO.OAuthResponse;
import com.beyondB.beyondB.dto.response.AuthResponseDTO.TokenRefreshResponse;
import com.beyondB.beyondB.security.handler.annotation.AuthUser;
import com.beyondB.beyondB.security.handler.annotation.ExtractToken;
import com.beyondB.beyondB.security.test.dto.LoginRequest;
import com.beyondB.beyondB.service.AuthService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Auth 🔐", description = "인증/인가 관련 API")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "카카오 로그인 API", description = "카카오 로그인 및 회원 가입을 진행하는 API입니다. by _루아_")
    @ApiResponses({
            @ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    @GetMapping("/login/kakao")
    public BaseResponse<OAuthResponse> kakaoLogin(@RequestParam("code") String code) {
        return BaseResponse.onSuccess(authService.kakaoLogin(code));
    }

    @Operation(summary = "로그인 API", description = "로그인 API입니다. by _루아_")
    @ApiResponses({
            @ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    @PostMapping("/login")
    public BaseResponse<OAuthResponse> login(@RequestBody LoginRequestDTO loginRequest) {
        return BaseResponse.onSuccess(authService.login(loginRequest));
    }

}
