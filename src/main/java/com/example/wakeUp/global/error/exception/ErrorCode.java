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

    EMPTY_STRING( 400, "비어있는 문자열입니다."),

    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(422, "사용자가 이미 존재합니다."),
    PASSWORD_MISMATCH(401, "비밀번호가 틀렸습니다."),
    NICKNAME_NOT_FOUND( 422, "이미 존재하는 닉네임입니다."),

    CODE_MISMATCH(401, "코드가 틀렸습니다."),
    EXPIRED_DATA(401, "만료된 데이터입니다."),
    ALREADY_LOGOUT(401, "로그아웃된 유저입니다."),

    ALREADY_REQUEST_SONG(422, "하루에 한 곡만 신청할 수 있습니다."),
    SONG_ALREADY_EXISTS(422, "오늘 이미 신청된 곡입니다."),
    SONG_NOT_FOUND(404, "노래를 찾을 수 없습니다."),
    USER_FORBIDDEN( 403, "유저에게 권한이 없습니다."),

    ALREADY_PUSH_UP(401, "업은 한 번만 누를 수 있습니다."),
    UP_NOT_FOUND(404, "업을 누른적이 없습니다."),


    CHART_NOT_FOUND(404, "차트를 찾을 수 없습니다."),

    PLAYLIST_FULL( 422, "10곡이 최대입니다"),

    ;


    private final int status;
    private final String message;
}
