package com.example.wakeUp.domain.user.domain;

import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.domain.user.domain.type.Role;
import com.example.wakeUp.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_tbl")
public class User extends BaseEntity {

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
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Song> songList = new ArrayList<>();

    @Builder
    public User(String name, String nickName, String email, String password, Role role, Song song) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.songList.add(song);
    }
}
