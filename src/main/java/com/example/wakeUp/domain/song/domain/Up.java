package com.example.wakeUp.domain.song.domain;

import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "up_tbl")
public class Up extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "song_id")
    private Song song;

    @Builder
    public Up(User user, Song song) {
        this.user = user;
        this.song = song;
    }

    public static Up createUp(User user, Song song) {
        Up up = new Up();
        up.confirmUser(user);
        up.confirmSong(song);
        return up;
    }

//    연관관계 편의 메서드
    public void confirmUser(User user) {
        this.user = user;
        user.getUps().add(this);
    }

    public void confirmSong(Song song) {
        this.song = song;
        song.getUps().add(this);
    }
}