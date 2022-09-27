package com.example.wakeUp.global.security.jwt.exception;

import com.example.wakeUp.global.error.exception.BusinessException;
import com.example.wakeUp.global.error.exception.ErrorCode;

public class AlreadyLogoutException extends BusinessException {

    public final static AlreadyLogoutException EXCEPTION = new AlreadyLogoutException();

    private AlreadyLogoutException() {
        super(ErrorCode.ALREADY_LOGOUT);
    }
}
