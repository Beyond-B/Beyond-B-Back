package com.beyondB.beyondB.apiPayload.exception;

import com.beyondB.beyondB.apiPayload.code.BaseErrorCode;

public class RecreationException extends GeneralException {

    public RecreationException(BaseErrorCode code) {
        super(code);
    }
}
