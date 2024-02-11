package com.beyondB.beyondB.controller;

import com.beyondB.beyondB.apiPayload.BaseResponse;
import com.beyondB.beyondB.converter.BookConverter;
import com.beyondB.beyondB.converter.DiaryConverter;
import com.beyondB.beyondB.dto.request.BookRequestDTO;
import com.beyondB.beyondB.dto.request.DiaryRequestDTO;
import com.beyondB.beyondB.dto.response.BookResponseDTO;
import com.beyondB.beyondB.dto.response.DiaryResponseDTO;
import com.beyondB.beyondB.entity.Book;
import com.beyondB.beyondB.entity.Diary;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.security.handler.annotation.AuthUser;
import com.beyondB.beyondB.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/book")
@RequiredArgsConstructor
public class bookController {
    private final BookService bookService;
    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "등록 성공")})
    @Operation(summary = "책 작성", description = "책 등록 API입니다.")
    @PostMapping("/create")
    public BaseResponse<BookResponseDTO.BookContentDTO> createDiary(
            @RequestBody BookRequestDTO.CreateBookDTO request) {

        Book book = bookService.createBook(request);

        return BaseResponse.onSuccess(BookConverter.toBookContentDTO(book));
    }
    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "조회 성공")})
    @Operation(summary = "책 상세 조회", description = "책 상세조회 API입니다.")
    @GetMapping("/{bookId}/detail")
    @Parameter(name = "user", hidden = true)
    @ResponseStatus(code = HttpStatus.OK)
    public BaseResponse<BookResponseDTO.DetailBookDTO> getDetailBook(@AuthUser User user, @PathVariable Long bookId) {
        BookResponseDTO.DetailBookDTO getBook = bookService.getDetailBook(user, bookId);

        return BaseResponse.onSuccess(getBook);
    }
}