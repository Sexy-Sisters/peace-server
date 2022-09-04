package com.example.wakeUp.domain.song.exception;

import com.example.wakeUp.global.error.exception.BusinessException;
import com.example.wakeUp.global.error.exception.ErrorCode;

public class EmptyStringException extends BusinessException {

    public static final EmptyStringException EXCEPTION = new EmptyStringException();

    private EmptyStringException() {
        super(ErrorCode.EMPTY_STRING);
    }
}
