package com.beyondB.beyondB.apiPayload.exception;

import com.beyondB.beyondB.apiPayload.code.BaseErrorCode;

public class BookQuizException extends GeneralException{
    public BookQuizException(BaseErrorCode code) {
        super(code);
    }
}
