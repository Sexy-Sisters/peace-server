package com.example.wakeUp.domain.user.exception;

import com.example.wakeUp.global.error.exception.BusinessException;
import com.example.wakeUp.global.error.exception.ErrorCode;

public class ExpiredDataException extends BusinessException {

    public final static ExpiredDataException EXCEPTION = new ExpiredDataException();

    private ExpiredDataException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
