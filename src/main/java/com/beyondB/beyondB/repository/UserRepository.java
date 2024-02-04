package com.beyondB.beyondB.repository;

import com.beyondB.beyondB.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); // 중복 가입 확인
}
