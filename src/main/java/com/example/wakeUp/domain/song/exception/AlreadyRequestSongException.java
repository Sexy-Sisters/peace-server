package com.example.wakeUp.domain.song.exception;

import com.example.wakeUp.global.error.exception.BusinessException;
import com.example.wakeUp.global.error.exception.ErrorCode;

public class AlreadyRequestSongException extends BusinessException {

    public final static AlreadyRequestSongException EXCEPTION = new AlreadyRequestSongException();

    private AlreadyRequestSongException() {
        super(ErrorCode.ALREADY_REQUEST_SONG);
    }
}
