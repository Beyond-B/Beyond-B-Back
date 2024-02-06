package com.beyondB.beyondB.controller;

import com.beyondB.beyondB.apiPayload.BaseResponse;
import com.beyondB.beyondB.apiPayload.code.status.SuccessStatus;
import com.beyondB.beyondB.converter.DiaryConverter;
import com.beyondB.beyondB.dto.request.DiaryRequestDTO;
import com.beyondB.beyondB.dto.response.DiaryResponseDTO;
import com.beyondB.beyondB.entity.Diary;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.security.handler.annotation.AuthUser;
import com.beyondB.beyondB.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "수정 성공")})
    @Operation(summary = "일기 수정", description = "일기 수정 API입니다.")
    @PatchMapping("/")
    @Parameter(name = "user", hidden = true)
    @ResponseStatus(code = HttpStatus.OK)
    public BaseResponse<DiaryResponseDTO.DiaryContentDTO> diaryResponseResult(
            @AuthUser User user,
            @Valid @RequestBody DiaryRequestDTO.UpdateDiaryDTO request) {
        Diary updatedDiary = diaryService.updateDiary(request);

        return BaseResponse.of(
                SuccessStatus._OK, DiaryConverter.toDiaryContentDTO(updatedDiary));
    }

    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "등록 성공")})
    @Operation(summary = "일기 작성", description = "일기 작성 API입니다.")
    @PostMapping("/create")
    @Parameter(name = "user", hidden = true)
    public BaseResponse<DiaryResponseDTO.DiaryContentDTO> createDiary(
            @AuthUser User user,
            @RequestBody DiaryRequestDTO.CreateDiaryDTO request) {

        Diary diary = diaryService.createDiary(request, user);

        return BaseResponse.onSuccess(DiaryConverter.toDiaryContentDTO(diary));
    }
    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "삭제 성공")})
    @Operation(summary = "일기 삭제", description = "일기 삭제 API입니다.")
    @DeleteMapping("/{diaryId}/delete")
    @ResponseStatus(code = HttpStatus.OK)
    public BaseResponse<String> deleteDiary(@PathVariable Long diaryId) {
        diaryService.deleteDiary(diaryId);

        return BaseResponse.onSuccess("해당 일기의 삭제가 완료되었습니다.");
    }

    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "조회 성공")})
    @Operation(summary = "일기 상세 조회", description = "일기 조회 API입니다.")
    @GetMapping("/{diaryId}/detail")
    @ResponseStatus(code = HttpStatus.OK)
    public BaseResponse<DiaryResponseDTO.DiaryContentDTO> getDetailDiary(@PathVariable Long diaryId) {
        Diary getDiary = diaryService.getDetailDiary(diaryId);

        return BaseResponse.onSuccess(DiaryConverter.toDiaryContentDTO(getDiary));
    }
    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "조회 성공")})
    @Operation(summary = "한 달 간 일기 조회", description = "한 달 간 일기 조회 API입니다.")
    @GetMapping("/monthly")
    @ResponseStatus(code = HttpStatus.OK)
    @Parameter(name = "user", hidden = true)
    public BaseResponse<DiaryResponseDTO.MonthlyDiaryDTO> getMonthlyDiary(
            @AuthUser User user,
            @RequestParam("year") int year,
            @RequestParam("month") int month) {
        List<DiaryResponseDTO.MonthlyDiarySummaryDTO> monthlyDiary = diaryService.getMonthlyDiary(user, year, month);

        return BaseResponse.onSuccess(DiaryConverter.toMonthlyDiaryDTO(monthlyDiary));
    }

}