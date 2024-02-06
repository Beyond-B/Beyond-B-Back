package com.beyondB.beyondB.converter;


import com.beyondB.beyondB.dto.KakaoProfile;
import com.beyondB.beyondB.dto.response.AuthResponseDTO.OAuthResponse;
import com.beyondB.beyondB.dto.response.AuthResponseDTO.TokenRefreshResponse;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.entity.enums.SocialType;

public class AuthConverter {

    public static User toUser(KakaoProfile kakaoProfile) {
        return User.builder()
                .username(kakaoProfile.getProperties().getNickname())
                .email(kakaoProfile.getKakao_account().getEmail())
                .socialType(SocialType.KAKAO)
                .build();
    }

    public static OAuthResponse toOAuthResponse(
            String accessToken, String refreshToken, Boolean isLogin, User user) {
        return OAuthResponse.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .isLogin(isLogin)
                .userId(user.getId())
                .build();
    }

    public static TokenRefreshResponse toTokenRefreshResponse(
            String accessToken, String refreshToken) {
        return TokenRefreshResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}

