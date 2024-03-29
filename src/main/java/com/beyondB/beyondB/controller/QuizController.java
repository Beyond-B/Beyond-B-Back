package com.beyondB.beyondB.controller;

import com.beyondB.beyondB.apiPayload.BaseResponse;
import com.beyondB.beyondB.converter.BookConverter;
import com.beyondB.beyondB.converter.QuizConverter;
import com.beyondB.beyondB.dto.request.BookRequestDTO;
import com.beyondB.beyondB.dto.request.QuizRequestDTO;
import com.beyondB.beyondB.dto.response.BookResponseDTO;
import com.beyondB.beyondB.dto.response.QuizResponseDTO;
import com.beyondB.beyondB.entity.Book;
import com.beyondB.beyondB.entity.BookQuiz;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.entity.mapping.UserBook;
import com.beyondB.beyondB.security.handler.annotation.AuthUser;
import com.beyondB.beyondB.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "등록 성공")})
    @Operation(summary = "책 퀴즈", description = "책 퀴즈 요청 API입니다.")
    @GetMapping("/{bookId}")
    @Parameter(name = "user", hidden = true)
    public  BaseResponse<QuizResponseDTO.QuizDetailDTO> getQuiz(@AuthUser User user, @PathVariable Long bookId) {

        BookQuiz requestedQuiz= quizService.getQuiz(user, bookId);

        return BaseResponse.onSuccess(QuizConverter.toQuizDetailDTO(requestedQuiz));
    }
    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "등록 성공")})
    @Operation(summary = "퀴즈 제출", description = "퀴즈 제출 API입니다.")
    @PostMapping("/submit")
    @Parameter(name = "user", hidden = true)
    public BaseResponse<Void> submitQuiz(
            @AuthUser User user,
            @Valid@RequestBody QuizRequestDTO.SubmitQuizDTO request) {
        quizService.submitQuiz(user, request);

        return BaseResponse.onSuccess(null);
    }
}
