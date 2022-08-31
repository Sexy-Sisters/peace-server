package com.example.wakeUp.domain.song.domain;

import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "song_tbl")
public class Song extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identify;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String singer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "song")
    private List<Up> ups = new ArrayList<>();

    @Builder
    public Song(String imgUrl, String title, String singer, User user, String identify) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.singer = singer;
        this.user = user;
        this.identify = identify;
    }
}
