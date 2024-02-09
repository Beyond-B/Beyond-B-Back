package com.beyondB.beyondB.apiPayload.exception;

import com.beyondB.beyondB.apiPayload.code.BaseErrorCode;

public class BookException extends GeneralException{
    public BookException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
