package com.example.wakeUp.domain.song.exception;

import com.example.wakeUp.global.error.exception.BusinessException;
import com.example.wakeUp.global.error.exception.ErrorCode;

public class AlreadyPushUpException extends BusinessException {

    public static final AlreadyPushUpException EXCEPTION = new AlreadyPushUpException();

    private AlreadyPushUpException () {
        super(ErrorCode.ALREADY_PUSH_UP);
    }
}
