package com.example.wakeUp.domain.user.exception;

import com.example.wakeUp.global.error.exception.BusinessException;
import com.example.wakeUp.global.error.exception.ErrorCode;

public class PasswordMisMatchException extends BusinessException {

    public final static PasswordMisMatchException EXCEPTION = new PasswordMisMatchException();

    private PasswordMisMatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
