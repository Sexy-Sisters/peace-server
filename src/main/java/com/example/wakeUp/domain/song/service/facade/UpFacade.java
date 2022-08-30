package com.example.wakeUp.domain.song.service.facade;

import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.domain.song.domain.Up;
import com.example.wakeUp.domain.song.domain.repository.UpRepository;
import com.example.wakeUp.domain.song.exception.AlreadyPushUpException;
import com.example.wakeUp.domain.song.exception.SongNotFoundException;
import com.example.wakeUp.domain.song.exception.UpNotFoundException;
import com.example.wakeUp.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpFacade {

    private final UpRepository upRepository;

    public void validatePushUp(User user, Song song) {
        if (upRepository.existsByUserAndSong(user, song)) {
            throw AlreadyPushUpException.EXCEPTION;
        }
    }

    public void validateCancelUp(User user, Song song) {
        if (!upRepository.existsByUserAndSong(user, song)) {
            throw SongNotFoundException.EXCEPTION;
        }
    }


    public Up findUpByUserSong(User user, Song song) {
        return upRepository.findByUserAndSong(user, song)
                .orElseThrow(() -> UpNotFoundException.EXCEPTION);
    }
}
