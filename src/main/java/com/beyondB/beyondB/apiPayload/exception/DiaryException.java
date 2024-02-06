package com.beyondB.beyondB.apiPayload.exception;

import com.beyondB.beyondB.apiPayload.code.BaseErrorCode;

public class DiaryException extends GeneralException{
    public DiaryException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
