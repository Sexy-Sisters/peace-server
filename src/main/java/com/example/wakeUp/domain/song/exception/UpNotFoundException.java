package com.example.wakeUp.domain.song.exception;

import com.example.wakeUp.global.error.exception.BusinessException;
import com.example.wakeUp.global.error.exception.ErrorCode;

public class UpNotFoundException extends BusinessException {

    public static final UpNotFoundException EXCEPTION = new UpNotFoundException();

    private UpNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
