package com.example.wakeUp.global.crawler;

import com.example.wakeUp.global.crawler.dto.SearchSongDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchSong {

    public List<SearchSongDto> search(String word) {
        String url = "https://music.bugs.co.kr/search/integrated?q=" + word;

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements trList = doc.select("#DEFAULT0 > table > tbody > tr");

        List<SearchSongDto> searchSongList = new ArrayList<>();
        for ( Element tr : trList ) {
            String imgUrl = tr.select("a > img").attr("src");
            String title = tr.select("th > p").text();
            String singer = tr.select("td > p").text();

            SearchSongDto searchSongDto = SearchSongDto.builder()
                    .imgUrl(imgUrl)
                    .title(title)
                    .singer(singer)
                    .build();

            searchSongList.add(searchSongDto);
        }

        return searchSongList;
    }
}
