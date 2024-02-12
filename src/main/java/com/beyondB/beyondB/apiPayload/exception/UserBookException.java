package com.beyondB.beyondB.apiPayload.exception;

import com.beyondB.beyondB.apiPayload.code.BaseErrorCode;

public class UserBookException extends GeneralException{
    public UserBookException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

