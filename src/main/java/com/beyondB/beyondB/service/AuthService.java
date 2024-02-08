package com.beyondB.beyondB.service;


import com.beyondB.beyondB.dto.request.LoginRequestDTO;
import com.beyondB.beyondB.dto.response.AuthResponseDTO.OAuthResponse;
import com.beyondB.beyondB.dto.response.AuthResponseDTO.TokenRefreshResponse;

public interface AuthService {

    OAuthResponse kakaoLogin(String code);

    OAuthResponse login(LoginRequestDTO loginRequest);

}
