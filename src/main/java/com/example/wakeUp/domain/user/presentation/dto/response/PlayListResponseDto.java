package com.example.wakeUp.domain.user.presentation.dto.response;

import com.example.wakeUp.domain.user.domain.PlayList;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlayListResponseDto {

    private Long id;
    private String imgUrl;
    private String title;
    private String singer;

    public static PlayListResponseDto of (PlayList playList) {
        return PlayListResponseDto.builder()
                .id(playList.getId())
                .imgUrl(playList.getImgUrl())
                .title(playList.getTitle())
                .singer(playList.getSinger())
                .build();
    }
}
