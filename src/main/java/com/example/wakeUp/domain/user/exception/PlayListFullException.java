package com.example.wakeUp.domain.user.exception;

import com.example.wakeUp.global.error.exception.BusinessException;
import com.example.wakeUp.global.error.exception.ErrorCode;

public class PlayListFullException extends BusinessException {

    public final static PlayListFullException EXCEPTION = new PlayListFullException();

    private PlayListFullException() {
        super(ErrorCode.PLAYLIST_FULL);
    }
}
