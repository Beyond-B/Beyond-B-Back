package com.beyondB.beyondB.controller;

import com.beyondB.beyondB.apiPayload.BaseResponse;
import com.beyondB.beyondB.converter.BookConverter;
import com.beyondB.beyondB.dto.request.BookRequestDTO;
import com.beyondB.beyondB.dto.response.BookResponseDTO;
import com.beyondB.beyondB.entity.Book;
import com.beyondB.beyondB.entity.BookAge;
import com.beyondB.beyondB.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

        return BaseResponse.onSuccess(BookConverter.toCreatBookDTO(book));
    }

}
