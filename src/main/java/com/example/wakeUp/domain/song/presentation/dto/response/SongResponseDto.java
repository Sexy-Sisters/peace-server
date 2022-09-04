package com.example.wakeUp.domain.song.presentation.dto.response;

import com.example.wakeUp.domain.song.domain.Song;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class SongResponseDto implements Serializable {

    private Long id;
    private String imgUrl;
    private String title;
    private String singer;
    private int numberOfUps;
    private String userName;
    private int createdHour;

    public static SongResponseDto of(Song song) {
        return SongResponseDto.builder()
                .id(song.getId())
                .imgUrl(song.getImgUrl())
                .title(song.getTitle())
                .singer(song.getSinger())
                .numberOfUps(song.getUps().size())
                .userName(song.getUser().getName())
                .createdHour(song.getCreatedAt().getHour())
                .build();
    }
}
