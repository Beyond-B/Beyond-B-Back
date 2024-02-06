package com.beyondB.beyondB.controller;

import com.beyondB.beyondB.apiPayload.BaseResponse;
import com.beyondB.beyondB.apiPayload.code.status.SuccessStatus;
import com.beyondB.beyondB.converter.DiaryConverter;
import com.beyondB.beyondB.dto.request.DiaryRequestDTO;
import com.beyondB.beyondB.dto.response.DiaryResponseDTO;
import com.beyondB.beyondB.entity.Diary;
import com.beyondB.beyondB.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;
    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "수정 성공")})
    @Operation(summary = "일기 수정", description = "일기 수정 API입니다.")
    @PatchMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    public BaseResponse<DiaryResponseDTO.DiaryResponseResultDTO> DiaryResponseResult(
//        @AuthUser User user,
        @Valid @RequestBody DiaryRequestDTO.UpdateDiaryDTO request) {
        Diary updatedDiary = diaryService.updateDiary(request);

        return BaseResponse.of(
                SuccessStatus._OK, DiaryConverter.toUpdateDiaryDTO(updatedDiary));
    }
    @ApiResponses({@ApiResponse(responseCode = "COMMON200", description = "삭제 성공")})
    @Operation(summary = "일기 삭제", description = "일기 삭제 API입니다.")
    @DeleteMapping("/{diaryId}/delete")
    @ResponseStatus(code = HttpStatus.OK)
    public BaseResponse<String> deleteDiary(@PathVariable Long diaryId) {
        diaryService.deleteDiary(diaryId);

        return BaseResponse.onSuccess("해당 일기의 삭제가 완료되었습니다.");
    }

}