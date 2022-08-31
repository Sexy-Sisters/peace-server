package com.example.wakeUp.domain.song.service;

import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.domain.song.domain.Up;
import com.example.wakeUp.domain.song.domain.repository.UpRepository;
import com.example.wakeUp.domain.song.facade.SongFacade;
import com.example.wakeUp.domain.song.facade.UpFacade;
import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpService {

    private final UpRepository upRepository;
    private final UserFacade userFacade;
    private final SongFacade songFacade;
    private final UpFacade upFacade;
    private final RankingService rankingService;

    @Transactional
    public void pushUp(Long id) {
        User user = userFacade.findByEmail(userFacade.securityUtil());
        Song song = songFacade.findSongById(id);
        upFacade.validatePushUp(user, song);
        upRepository.save(Up.createUp(user, song));

        rankingService.push(song.getIdentify(), song.getUps().size());
    }

    @Transactional
    public void cancelUp(Long id) {
        User user = userFacade.findByEmail(userFacade.securityUtil());
        Song song = songFacade.findSongById(id);
        upFacade.validateCancelUp(user, song);
        upRepository.delete(upFacade.findUpByUserSong(user, song));

        rankingService.push(song.getIdentify(), song.getUps().size());
    }
}
