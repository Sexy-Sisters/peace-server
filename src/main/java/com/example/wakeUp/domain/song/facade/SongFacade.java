package com.example.wakeUp.domain.song.facade;

import com.example.wakeUp.domain.song.presentation.dto.request.CreateSongRequestDto;
import com.example.wakeUp.domain.song.domain.repository.SongRepository;
import com.example.wakeUp.domain.song.exception.SongAlreadyExistsException;
import com.example.wakeUp.domain.song.exception.AlreadyRequestSongException;
import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.global.Utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SongFacade {

    private final SongRepository songRepository;

    public void validateRequestSong(CreateSongRequestDto dto, User user) {

        if (songRepository.existsByUserAndCreatedAtBetween(user, DateUtil.getToday(), DateUtil.getTomorrow()))
            throw AlreadyRequestSongException.EXCEPTION;

        else if (songRepository.existsByTitleAndSingerAndCreatedAtBetween(dto.getTitle(), dto.getSinger(), DateUtil.getToday(), DateUtil.getTomorrow()))
            throw SongAlreadyExistsException.EXCEPTION;
    }
}
