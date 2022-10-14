package com.example.wakeUp.domain.user.domain.repository;

import com.example.wakeUp.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByName(String name);

    Optional<User> findByEmail(String email);

    @Query( "SELECT u " +
            "FROM User u " +
            "ORDER BY u.playList.size DESC ")
    List<User> findAllOrderByPlayListSize();
}
