package com.example.wakeUp.global.security.jwt.exception;

import com.example.wakeUp.global.error.exception.BusinessException;
import com.example.wakeUp.global.error.exception.ErrorCode;

public class InvalidTokenException extends BusinessException {

    public static final InvalidTokenException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
