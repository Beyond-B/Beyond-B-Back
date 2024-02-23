package com.beyondB.beyondB.controller;

import com.beyondB.beyondB.apiPayload.BaseResponse;
import com.beyondB.beyondB.converter.UserConverter;
import com.beyondB.beyondB.dto.UserSignupDTO;
import com.beyondB.beyondB.dto.request.UserRequestDTO.PatchAgeDTO;
import com.beyondB.beyondB.dto.response.UserResponseDTO.UserDetailDTO;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.security.handler.annotation.AuthUser;
import com.beyondB.beyondB.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public BaseResponse<String> signUp(@RequestBody UserSignupDTO userSignUpDto) throws Exception {
        userService.signUp(userSignUpDto);
        return BaseResponse.onSuccess("회원가입 성공");
    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }

    @GetMapping("/test")
    public String test() {
        return "test 요청 성공";
    }

    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "조회 성공")})
    @Operation(summary = "마이페이지", description = "마이페이지 조회 API입니다.")
    @GetMapping("/mypage")
    @Parameter(name = "user", hidden = true)
    public BaseResponse<UserDetailDTO> myPage(@AuthUser User user) {
        return BaseResponse.onSuccess(UserConverter.toUserDetailDTO(user));
    }

    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "조회 성공")})
    @Operation(summary = "나이 수정 API", description = "나이 수정 API입니다.")
    @PatchMapping("/age")
    @Parameter(name = "user", hidden = true)
    public BaseResponse<UserDetailDTO> patchAge(@AuthUser User user, @RequestBody PatchAgeDTO patchAgeDTO) {
        User newUser = userService.patchAge(user, patchAgeDTO);
        return BaseResponse.onSuccess(UserConverter.toUserDetailDTO(newUser));
    }

    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "삭제 성공")})
    @Operation(summary = "회원탈퇴 API", description = "회원탈퇴 API입니다.")
    @Parameter(name = "user", hidden = true)
    @DeleteMapping("/user")
    public BaseResponse<String> deleteUser(@AuthUser User user) {
        userService.deleteUser(user);
        return BaseResponse.onSuccess("회원탈퇴 성공");
    }
}
