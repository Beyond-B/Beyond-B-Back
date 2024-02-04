package com.beyondB.beyondB.apiPayload.exception;

import com.beyondB.beyondB.apiPayload.code.BaseErrorCode;

public class S3Exception extends GeneralException {

    public S3Exception(BaseErrorCode code) {
        super(code);
    }
}
