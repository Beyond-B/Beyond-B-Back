package com.beyondB.beyondB.apiPayload.code.status;

import com.beyondB.beyondB.apiPayload.code.BaseErrorCode;
import com.beyondB.beyondB.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    // 기본 에러
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 공통 에러
    PAGE_UNDER_ZERO(HttpStatus.BAD_REQUEST, "COMM_001", "페이지는 0이상이어야 합니다."),

    // S3 관련
    S3_OBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "S3_001", "S3 오브젝트를 찾을 수 없습니다."),
    S3_UPLOAD_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "S3_002", "S3 업로드 실패"),

    // Auth 관련
    AUTH_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_001", "토큰이 만료되었습니다."),
    AUTH_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_002", "토큰이 유효하지 않습니다."),
    INVALID_LOGIN_REQUEST(HttpStatus.UNAUTHORIZED, "AUTH_003", "올바른 이메일이나 패스워드가 아닙니다."),
    INVALID_REQUEST_INFO(HttpStatus.UNAUTHORIZED, "AUTH_006", "카카오 정보 불러오기에 실패하였습니다."),
    NOT_EQUAL_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_007", "리프레시 토큰이 다릅니다."),
    NOT_CONTAIN_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_008", "해당하는 토큰이 저장되어있지 않습니다."),

    // User 관련
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH_004", "존재하지 않는 사용자입니다."),

    // Recreation 관련
    SEARCH_CONDITION_INVALID(HttpStatus.BAD_REQUEST, "RECR_001", "검색 조건이 하나라도 존재해야 합니다."),
    RECREATION_NOT_FOUND(HttpStatus.NOT_FOUND, "RECR_002", "존재하지 않는 레크레이션입니다."),

    // RecreationReview 관련
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REV_001", "존재하지 않는 리뷰입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder().message(message).code(code).isSuccess(false).build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}