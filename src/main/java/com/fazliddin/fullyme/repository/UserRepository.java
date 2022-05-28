package com.fazliddin.fullyme.repository;

import com.fazliddin.fullyme.entity.User;
import com.fazliddin.fullyme.enums.RoleTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String phoneNumber);
}
