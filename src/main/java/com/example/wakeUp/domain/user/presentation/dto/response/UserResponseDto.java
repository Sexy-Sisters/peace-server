package com.example.wakeUp.domain.user.presentation.dto.response;

import com.example.wakeUp.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {

    private Long id;
    private String profileImg;
    private String nickName;
    private String name;

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .profileImg(user.getProfileImg())
                .nickName(user.getNickName())
                .name(user.getName())
                .build();
    }
}
