package com.example.wakeUp.domain.user.presentation.dto.request;

import com.example.wakeUp.domain.user.domain.PlayList;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class AddPlayListRequestDto {

    @NotEmpty
    private String imgUrl;

    @NotEmpty
    @Size(min = 1)
    private String title;

    @NotEmpty
    @Size(min = 1)
    private String singer;

    public PlayList toEntity() {
        return PlayList.builder()
                .imgUrl(imgUrl)
                .title(title)
                .singer(singer)
                .build();
    }
}
