package com.example.wakeUp.domain.user.exception;

import com.example.wakeUp.global.error.exception.BusinessException;
import com.example.wakeUp.global.error.exception.ErrorCode;

public class NickNameAlreadyExistsException extends BusinessException {

    public static final NickNameAlreadyExistsException EXCEPTION = new NickNameAlreadyExistsException();

    private NickNameAlreadyExistsException() {
        super(ErrorCode.NICKNAME_NOT_FOUND);
    }
}
