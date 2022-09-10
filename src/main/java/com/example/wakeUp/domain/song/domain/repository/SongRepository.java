package com.example.wakeUp.domain.song.domain.repository;

import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.domain.user.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {

    boolean existsByUserAndCreatedAtBetween(User user, LocalDateTime today, LocalDateTime tomorrow);

    boolean existsByTitleAndSingerAndCreatedAtBetween(String title, String singer, LocalDateTime today, LocalDateTime tomorrow);

    @EntityGraph(attributePaths = {"ups", "user"})
    Optional<Song> findByIdentify(String identify);
}

