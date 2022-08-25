package com.example.wakeUp.domain.song.presentation.dto.request;

import com.example.wakeUp.domain.song.domain.Song;
import lombok.Getter;

@Getter
public class CreateSongRequestDto {

    private String imgUrl;
    private String title;
    private String singer;

    public Song toEntity() {
        return Song.builder()
                .imgUrl(imgUrl)
                .title(title)
                .singer(singer)
                .build();
    }
}
