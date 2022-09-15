package com.example.wakeUp.domain.song.exception;

import com.example.wakeUp.global.error.exception.BusinessException;
import com.example.wakeUp.global.error.exception.ErrorCode;

public class UserForbiddenException extends BusinessException {

    public static final UserForbiddenException EXCEPTION = new UserForbiddenException();

    private UserForbiddenException() {
        super(ErrorCode.USER_FORBIDDEN);
    }
}
