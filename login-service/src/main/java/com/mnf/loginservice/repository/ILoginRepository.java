package com.mnf.loginservice.repository;

import com.mnf.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ILoginRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
}
