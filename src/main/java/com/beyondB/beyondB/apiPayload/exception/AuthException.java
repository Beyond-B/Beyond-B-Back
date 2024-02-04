package com.beyondB.beyondB.apiPayload.exception;

import com.beyondB.beyondB.apiPayload.code.BaseErrorCode;

public class AuthException extends GeneralException {

    public AuthException(BaseErrorCode code) {
        super(code);
    }
}
