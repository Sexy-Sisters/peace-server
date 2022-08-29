package com.example.wakeUp.domain.song.presentation.dto.request;

import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateSongRequestDto {

    private String imgUrl;
    private String title;
    private String singer;

    public Song toEntity(User user) {
        return Song.builder()
                .imgUrl(imgUrl)
                .title(title)
                .singer(singer)
                .user(user)
                .build();
    }
}
