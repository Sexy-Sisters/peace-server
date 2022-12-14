package com.example.wakeUp.domain.song.facade;

import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.domain.song.domain.repository.SongRepository;
import com.example.wakeUp.domain.song.exception.*;
import com.example.wakeUp.domain.song.presentation.dto.request.CreateSongRequestDto;
import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.global.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SongFacade {

    private final SongRepository songRepository;

    public void validateRequestSong(CreateSongRequestDto dto, User user) {
        if (StringUtils.isEmpty(dto.getTitle()) && StringUtils.isEmpty(dto.getSinger()))
            throw EmptyStringException.EXCEPTION;

        if (songRepository.existsByUserAndCreatedAtBetween(user, DateUtil.getToday(), DateUtil.getTomorrow()))
            throw AlreadyRequestSongException.EXCEPTION;

        if (songRepository.existsByTitleAndSingerAndCreatedAtBetween(dto.getTitle(), dto.getSinger(), DateUtil.getToday(), DateUtil.getTomorrow()))
            throw SongAlreadyExistsException.EXCEPTION;
    }

    public Song findSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> SongNotFoundException.EXCEPTION);
    }

    public Song findSongByIdentify(String identify) {
        return songRepository.findByIdentify(identify)
                .orElseThrow(() -> SongNotFoundException.EXCEPTION);
    }

    public Song findTodaySongByUser(User user, LocalDateTime today, LocalDateTime tomorrow) {
        return songRepository.findByUserAndCreatedAtBetween(user, today, tomorrow)
                .orElse(null);
    }

    public void validateDeleteSong(Song song, User user) {
        if (!song.getUser().getEmail().equals(user.getEmail())) {
            throw UserForbiddenException.EXCEPTION;
        }
    }
}
