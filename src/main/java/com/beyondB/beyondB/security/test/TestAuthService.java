package com.beyondB.beyondB.security.test;

import com.beyondB.beyondB.apiPayload.code.status.ErrorStatus;
import com.beyondB.beyondB.apiPayload.exception.AuthException;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.repository.UserRepository;
import com.beyondB.beyondB.security.jwt.service.JwtService;
import com.beyondB.beyondB.security.test.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestAuthService {

    private final UserRepository userRepository;
    private final JwtService jwtTokenProvider;

    @Transactional
    public LoginResponse login(String email) {

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(() -> new AuthException(ErrorStatus.USER_NOT_FOUND));

        String accessToken = jwtTokenProvider.createAccessToken(String.valueOf(user.getId()));

        return LoginResponse.builder().accessToken(accessToken).build();
    }
}
