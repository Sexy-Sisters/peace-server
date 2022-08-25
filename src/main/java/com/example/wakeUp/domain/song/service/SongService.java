package com.example.wakeUp.domain.song.service;

import com.example.wakeUp.domain.song.presentation.dto.request.CreateSongRequestDto;
import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.domain.song.domain.repository.SongRepository;
import com.example.wakeUp.domain.song.facade.SongFacade;
import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final UserFacade userFacade;
    private final SongFacade songFacade;

    @Transactional
    public void requestSong(CreateSongRequestDto dto) {

        User user = userFacade.getCurrentUser();
        songFacade.validateRequestSong(dto, user);

        Song song = dto.toEntity();
        song.setRelation(user);

        songRepository.save(song);
    }
}
