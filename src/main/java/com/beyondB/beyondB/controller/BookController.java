package com.beyondB.beyondB.controller;

import com.beyondB.beyondB.apiPayload.BaseResponse;
import com.beyondB.beyondB.converter.BookConverter;
import com.beyondB.beyondB.dto.request.BookRequestDTO;
import com.beyondB.beyondB.dto.response.BookResponseDTO;
import com.beyondB.beyondB.entity.Book;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.entity.enums.Emotion;
import com.beyondB.beyondB.entity.mapping.UserBook;
import com.beyondB.beyondB.security.handler.annotation.AuthUser;
import com.beyondB.beyondB.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("")
    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "등록 성공")})
    @Operation(summary = "기분별 책 조회", description = "기분별 책 조회 API입니다. 퀴즈 푼 여부도 확인할수 있습니다.")
    @Parameter(name = "user", hidden = true)
    public BaseResponse<List<BookResponseDTO.BookPreviewDTO>> getBookPreview(
            @AuthUser User user, @RequestParam Emotion emotion) {
        List<Book> bookPreview = bookService.getBookPreview(emotion, user);
        List<UserBook> userBooks = bookService.getUserBooks(user);

        return BaseResponse.onSuccess(BookConverter.toBookPreviewDTO(bookPreview, userBooks));
    }

    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "등록 성공")})
    @Operation(summary = "책 작성", description = "책 등록 API입니다.")
    @PostMapping("/create")
    public BaseResponse<BookResponseDTO.BookContentDTO> createDiary(
            @RequestBody BookRequestDTO.CreateBookDTO request) {

        Book book = bookService.createBook(request);

        return BaseResponse.onSuccess(BookConverter.toCreatBookDTO(book));
    }
}