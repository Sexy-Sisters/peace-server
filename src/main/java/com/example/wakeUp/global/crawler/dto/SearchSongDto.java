package com.example.wakeUp.global.crawler.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString(of = {"imgUrl", "title", "singer"})
public class SearchSongDto {

    private String imgUrl;
    private String title;
    private String singer;
}
