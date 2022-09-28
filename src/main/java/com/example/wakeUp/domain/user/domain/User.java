package com.example.wakeUp.domain.user.domain;

import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.domain.song.domain.Up;
import com.example.wakeUp.domain.user.domain.type.Authority;
import com.example.wakeUp.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_tbl")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_img", nullable = false)
    private String profileImg;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Authority authority;

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Song> songList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Up> ups = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<PlayList> playList = new ArrayList<>();

    @Builder
    public User(String profileImg, String name, String nickName, String email, String password, Authority authority) {
        this.profileImg = profileImg;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }

    public void updateProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}
