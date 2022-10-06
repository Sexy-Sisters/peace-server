package com.example.wakeUp.domain.user.presentation;

import com.example.wakeUp.domain.user.presentation.dto.request.AddPlayListRequestDto;
import com.example.wakeUp.domain.user.presentation.dto.response.PlayListResponseDto;
import com.example.wakeUp.domain.user.service.PlayListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name="플레이리스트", description = "플레이리스트 관련 API 명세서입니다.")
@RestController
@RequestMapping("/api/playlist")
@RequiredArgsConstructor
public class PlayListController {

    private final PlayListService playListService;

    @Operation(summary = "플레이리스트에 노래 추가")
    @PostMapping
    public void addPlayList(@RequestBody @Valid AddPlayListRequestDto dto) {
        playListService.addPlayList(dto);
    }

    @Operation(summary = "유저 아이디로 플레이리스트 조회")
    @GetMapping("/{userId}")
    public List<PlayListResponseDto> findPlayList(@PathVariable Long userId) {
        return playListService.findPlayList(userId);
    }

    @Operation(summary = "음악 아이디로 플레이리스트에서 음악 삭제")
    @DeleteMapping("/{id}")
    public void deletePlayList(@PathVariable Long id) {
        playListService.deletePlayList(id);
    }
}
