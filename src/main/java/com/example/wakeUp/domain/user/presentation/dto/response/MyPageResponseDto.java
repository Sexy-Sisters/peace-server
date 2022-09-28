package com.example.wakeUp.domain.user.presentation.dto.response;

import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.domain.song.presentation.dto.response.SongResponseDto;
import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.domain.user.domain.type.Authority;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyPageResponseDto {

    private Long id;
    private String profileImg;
    private String name;
    private String nickName;
    private String email;
    private Authority authority;
    private SongResponseDto requestedSong;

    public static MyPageResponseDto of(User user, Song song) {
        SongResponseDto songResponseDto =
                song == null ? null : SongResponseDto.of(song);
        return MyPageResponseDto.builder()
                .id(user.getId())
                .profileImg(user.getProfileImg())
                .name(user.getName())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .authority(user.getAuthority())
                .requestedSong(songResponseDto)
                .build();
    }
}
