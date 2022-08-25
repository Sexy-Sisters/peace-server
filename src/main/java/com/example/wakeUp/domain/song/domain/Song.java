package com.example.wakeUp.domain.song.domain;

import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "song_tbl")
public class Song extends BaseEntity {

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String singer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setRelation(User user) {
        user.getSongList().add(this);
        this.user = user;
    }

    @Builder
    public Song(String imgUrl, String title, String singer, User user) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.singer = singer;
        this.user = user;
    }
}
