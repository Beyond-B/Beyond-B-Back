package com.beyondB.beyondB.service.impl;


import com.beyondB.beyondB.apiPayload.code.status.ErrorStatus;
import com.beyondB.beyondB.apiPayload.exception.AuthException;
import com.beyondB.beyondB.converter.AuthConverter;
import com.beyondB.beyondB.dto.KakaoProfile;
import com.beyondB.beyondB.dto.OAuthToken;
import com.beyondB.beyondB.dto.request.LoginRequestDTO;
import com.beyondB.beyondB.dto.response.AuthResponseDTO.OAuthResponse;
import com.beyondB.beyondB.dto.response.AuthResponseDTO.TokenRefreshResponse;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.entity.enums.SocialType;
import com.beyondB.beyondB.repository.UserRepository;
import com.beyondB.beyondB.security.provider.JwtTokenProvider;
import com.beyondB.beyondB.security.provider.KakaoAuthProvider;
import com.beyondB.beyondB.security.test.dto.LoginResponse;
import com.beyondB.beyondB.service.AuthService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final KakaoAuthProvider kakaoAuthProvider;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public OAuthResponse kakaoLogin(String code) {
        OAuthToken oAuthToken = kakaoAuthProvider.requestToken(code);
        KakaoProfile kakaoProfile =
                kakaoAuthProvider.requestKakaoProfile(oAuthToken.getAccess_token());

        // 유저 정보 받기
        Optional<User> queryUser =
                userRepository.findByEmailAndSocialType(
                        kakaoProfile.getKakao_account().getEmail(), SocialType.KAKAO);

        // 가입자 혹은 비가입자 체크해서 로그인 처리
        if (queryUser.isPresent()) {
            User user = queryUser.get();
            String accessToken = jwtTokenProvider.createAccessToken(user.getId());
            String refreshToken = jwtTokenProvider.createRefreshToken(user.getId());
            return AuthConverter.toOAuthResponse(accessToken, refreshToken, true, user);
        } else {
            User user = userRepository.save(AuthConverter.toUser(kakaoProfile));
            String accessToken = jwtTokenProvider.createAccessToken(user.getId());
            String refreshToken = jwtTokenProvider.createRefreshToken(user.getId());
            return AuthConverter.toOAuthResponse(accessToken, refreshToken, false, user);
        }
    }

    @Override
    public OAuthResponse login(LoginRequestDTO loginRequest) {
        User user =
                userRepository
                        .findByEmail(loginRequest.getEmail())
                        .orElseThrow(() -> new AuthException(ErrorStatus.USER_NOT_FOUND));

        String accessToken;
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            accessToken = jwtTokenProvider.createAccessToken(user.getId());
        } else {
            throw new AuthException(ErrorStatus.INVALID_PASSWORD);
        }

        return OAuthResponse.builder().accessToken(accessToken).build();
    }
}
