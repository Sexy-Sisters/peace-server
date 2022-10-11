package com.example.wakeUp.global.crawler;

import com.example.wakeUp.global.crawler.dto.SearchSongDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
        int size = Math.min(trList.size(), 10);
        if (!trList.isEmpty()) {
            for ( int i = 0; i < size; i++ ) {
                String imgUrl = trList.get(i).select("td > a > img").attr("src");
                String title = trList.get(i).select("td.info > a.title.ellipsis").text();
                String singer = trList.get(i).select("td.info > a.artist.ellipsis").text();

                if (title.startsWith("TITLE ")) {
                    title = title.substring(6, title.length());
                }
                if (title.startsWith("19금 ")) {
                    title = title.substring(4, title.length());
                }


                SearchSongDto searchSongDto = SearchSongDto.builder()
                        .imgUrl(imgUrl)
                        .title(title)
                        .singer(singer)
                        .build();

                searchSongList.add(searchSongDto);
            }
        }

        return searchSongList;
    }
}
