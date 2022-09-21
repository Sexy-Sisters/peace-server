package com.example.wakeUp.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "play_list_tbl")
public class PlayList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "play_list_id", nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String singer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public PlayList(String imgUrl, String title, String singer, User user) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.singer = singer;
        this.user = user;
    }
}
