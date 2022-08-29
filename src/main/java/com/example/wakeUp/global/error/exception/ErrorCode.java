package com.example.wakeUp.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "서버에 오류가 발생했습니다."),
    BAD_REQUEST(400, "잘못된 요청입니다."),

    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(401, "만료된 토큰입니다."),

    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(422, "사용자가 이미 존재합니다."),
    PASSWORD_MISMATCH(401, "비밀번호가 틀렸습니다."),

    CODE_MISMATCH(401, "코드가 틀렸습니다."),
    EXPIRED_DATA(401, "만료된 데이터입니다."),
    ALREADY_LOGOUT(401, "로그아웃된 유저입니다."),

    ALREADY_REQUEST_SONG(422, "하루에 한 곡만 신청할 수 있습니다."),
    SONG_ALREADY_EXISTS(422, "오늘 이미 신청된 곡입니다."),
    SONG_NOT_FOUND(404, "노래를 찾을 수 없습니다."),
    ;

    private final int status;
    private final String message;
}
