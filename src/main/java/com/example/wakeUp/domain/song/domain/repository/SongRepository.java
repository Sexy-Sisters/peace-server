package com.example.wakeUp.domain.song.domain.repository;

import com.example.wakeUp.domain.song.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
