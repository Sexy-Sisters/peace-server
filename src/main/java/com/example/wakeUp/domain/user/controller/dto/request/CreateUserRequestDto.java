package com.example.wakeUp.domain.user.domain.controller.dto.request;

import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.domain.user.domain.type.Role;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class CreateUserRequestDto {

    private String name;
    private String nickName;
    private String email;
    private String password;

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .name(name)
                .nickName(nickName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.ROLE_USER)
                .build();
    }
}
