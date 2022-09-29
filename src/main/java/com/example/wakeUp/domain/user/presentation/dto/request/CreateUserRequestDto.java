package com.example.wakeUp.domain.user.presentation.dto.request;

import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.domain.user.domain.type.Authority;
import com.example.wakeUp.global.s3.DefaultProfileImg;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;

@Getter
public class CreateUserRequestDto {

    @NotNull(message = "이름을 입력해주세요")
    private String name;
    @NotNull(message = "닉네임을 입력해주세요")
    private String nickName;
    @NotNull(message = "이메일을 입력해주세요")
    private String email;
    @NotNull(message = "비밀번호를 입력해주세요")
    private String password;

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .profileImg(DefaultProfileImg.DEFAULT_PROFILE_IMG)
                .name(name)
                .nickName(nickName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }
}
