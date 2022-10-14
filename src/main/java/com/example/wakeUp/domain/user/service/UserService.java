package com.example.wakeUp.domain.user.service;

import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.domain.song.facade.SongFacade;
import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.domain.user.domain.repository.UserRepository;
import com.example.wakeUp.domain.user.facade.UserFacade;
import com.example.wakeUp.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.example.wakeUp.domain.user.presentation.dto.response.MyPageResponseDto;
import com.example.wakeUp.domain.user.presentation.dto.response.UserResponseDto;
import com.example.wakeUp.global.redis.RedisService;
import com.example.wakeUp.global.s3.S3Properties;
import com.example.wakeUp.global.s3.S3Service;
import com.example.wakeUp.global.utils.DateUtil;
import com.example.wakeUp.global.utils.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceImp{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;
    private final MailService mailService;
    private final RedisService redisService;
    private final SongFacade songFacade;
    private final S3Service s3Service;

    @Override
    public void signUp(CreateUserRequestDto request) {
        userFacade.validateSignUp(request.getEmail(), request.getName());
        userRepository.save(request.toEntity(passwordEncoder));
    }

    @Transactional
    public void issueCode(String email) {

        String code = RandomUtil.issue();
        redisService.setDataExpire(email, code, Duration.ofMinutes(3));

        mailService.sendMail(
                email,
                "ISSUE CODE",
                mailService.genTemplateEngine("issue-code", Map.of("code", code)),
                true);
    }

    @Transactional
    public boolean checkCode(String code, String email) {
        userFacade.checkCode(code, email);
        redisService.delete(email);
        return true;
    }

    @Transactional(readOnly = true)
    public MyPageResponseDto findMyPage() {
        User user = userFacade.getCurrentUser();
        Song song = songFacade.findTodaySongByUser(user, DateUtil.getToday(), DateUtil.getTomorrow());
        return MyPageResponseDto.of(user, song);
    }

    @Transactional
    public String updateProfile(MultipartFile multipartFile) throws IOException {
        User user = userFacade.getCurrentUser();
        String url = s3Service.upload(multipartFile, S3Properties.USER_PROFILE);

        user.updateProfileImg(url);
        userRepository.save(user);

        return url;
    }
    
    @Transactional(readOnly = true)
    public List<UserResponseDto> findUsers() {
        return userRepository.findAllOrderByPlayListSize().stream()
                .map(UserResponseDto::of)
                .collect(Collectors.toList());
    }
}