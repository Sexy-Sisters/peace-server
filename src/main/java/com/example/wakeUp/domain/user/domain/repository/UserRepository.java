package com.example.wakeUp.domain.user.domain.repository;

import com.example.wakeUp.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
