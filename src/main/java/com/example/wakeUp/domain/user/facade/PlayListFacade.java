package com.example.wakeUp.domain.user.facade;

import com.example.wakeUp.domain.song.exception.SongAlreadyExistsException;
import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.domain.user.domain.repository.PlayListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayListFacade {

    private final PlayListRepository playListRepository;

    public void validateAddPlayList(User user, String title, String singer) {
        user.getPlayList().forEach(playList -> {
            if (playList.getTitle().equals(title) && playList.getSinger().equals(singer)) {
                throw SongAlreadyExistsException.EXCEPTION;
            }
        });
    }
}
