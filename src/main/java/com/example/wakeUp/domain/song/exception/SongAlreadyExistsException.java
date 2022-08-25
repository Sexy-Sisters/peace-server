package com.example.wakeUp.domain.song.exception;

import com.example.wakeUp.global.error.exception.BusinessException;
import com.example.wakeUp.global.error.exception.ErrorCode;

public class SongAlreadyExistsException extends BusinessException {

    public final static SongAlreadyExistsException EXCEPTION = new SongAlreadyExistsException();

    private SongAlreadyExistsException() {
        super(ErrorCode.SONG_ALREADY_EXISTS);
    }
}
