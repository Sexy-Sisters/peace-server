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
        String url = "https://www.genie.co.kr/search/searchMain?query=" + word;

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements trList = doc.select("#body-content > div.search_song > div.search_result_detail > div > table > tbody > tr");

        List<SearchSongDto> searchSongList = new ArrayList<>();
        for ( Element tr : trList ) {
            String imgUrl = tr.select("td > a > img").attr("src");
            String title = tr.select("td.info > a.title.ellipsis").text();
            String singer = tr.select("td.info > a.artist.ellipsis").text();

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
