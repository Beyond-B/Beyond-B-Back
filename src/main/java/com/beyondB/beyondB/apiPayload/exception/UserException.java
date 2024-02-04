package com.beyondB.beyondB.apiPayload.exception;

import com.beyondB.beyondB.apiPayload.code.BaseErrorCode;

public class UserException extends GeneralException {

    public UserException(BaseErrorCode code) {
        super(code);
    }
}
