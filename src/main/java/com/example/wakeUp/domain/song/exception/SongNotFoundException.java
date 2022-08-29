package com.example.wakeUp.domain.song.exception;

import com.example.wakeUp.global.error.exception.BusinessException;
import com.example.wakeUp.global.error.exception.ErrorCode;

public class SongNotFoundException extends BusinessException {

    public static final SongNotFoundException EXCEPTION = new SongNotFoundException();

    private SongNotFoundException() {
        super(ErrorCode.SONG_NOT_FOUND);
    }
}
