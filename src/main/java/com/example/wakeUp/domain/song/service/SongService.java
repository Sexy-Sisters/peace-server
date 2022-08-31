package com.example.wakeUp.domain.song.service;

import com.example.wakeUp.domain.song.presentation.dto.request.CreateSongRequestDto;
import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.domain.song.domain.repository.SongRepository;
import com.example.wakeUp.domain.song.facade.SongFacade;
import com.example.wakeUp.domain.song.presentation.dto.response.SongResponseDto;
import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.domain.user.domain.repository.UserRepository;
import com.example.wakeUp.domain.user.facade.UserFacade;
import com.example.wakeUp.global.Utils.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongService {

    private final SongRepository songRepository;
    private final UserFacade userFacade;
    private final SongFacade songFacade;
    private final RankingService rankingService;

    @Transactional
    public void requestSong(CreateSongRequestDto dto) {
        User user = userFacade.getCurrentUser();
        songFacade.validateRequestSong(dto, user);
        Song song = dto.toEntity(user);

        rankingService.push(song.getIdentify(), song.getUps().size());
        songRepository.save(song);
    }

    @Transactional
    public void deleteSong(Long id) {
        Song song = songFacade.findSongById(id);

        rankingService.remove(song.getIdentify());
        songRepository.delete(song);
    }
}
