package com.example.wakeUp.domain.song.domain.repository;

import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.domain.song.domain.Up;
import com.example.wakeUp.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UpRepository extends JpaRepository<Up, Long> {
    Optional<Up> findByUserAndSong(User user, Song song);

    boolean existsByUserAndSong(User user, Song song);

    void deleteBySong(Song song);
}
