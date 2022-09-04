package com.example.wakeUp.domain.chart.domain;

import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chart_tbl")
public class Chart extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "img_url", nullable = false, unique = true)
    private String imgUrl;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, unique = true)
    private String singer;

    @Column(nullable = false, unique = true)
    private int point;

    @OneToMany(mappedBy = "chart")
    private List<Like> likes = new ArrayList<>();

    @Builder
    public Chart (String imgUrl, String title, String singer, int point) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.singer = singer;
        this.point = point;
    }

    public static Chart createChart(Song song) {
        return Chart.builder()
                .imgUrl(song.getImgUrl())
                .title(song.getTitle())
                .singer(song.getSinger())
                .point(0)
                .build();
    }
}
