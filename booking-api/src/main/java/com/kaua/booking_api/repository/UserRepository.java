package com.kaua.booking_api.repository;

import com.kaua.booking_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsUser(Long id);

}
