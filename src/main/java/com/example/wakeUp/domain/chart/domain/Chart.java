package com.example.wakeUp.domain.chart.domain;

import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chart_tbl")
public class Chart extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String singer;

    @Column(nullable = false)
    private int point;

    @Column(name = "redis_key", nullable = false, unique = true)
    private String redisKey;

    @Builder
    public Chart (String imgUrl, String title, String singer, int point, String redisKey) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.singer = singer;
        this.point = point;
        this.redisKey = redisKey;
    }

    public static Chart createChart(Song song) {
        return Chart.builder()
                .imgUrl(song.getImgUrl())
                .title(song.getTitle())
                .singer(song.getSinger())
                .point(0)
                .redisKey(song.getTitle()+"@"+song.getSinger())
                .build();
    }

    public void increasePoint() {
        this.point++;
    }

    public void decreasePoint(int n) {
        this.point -= n;
    }
}
