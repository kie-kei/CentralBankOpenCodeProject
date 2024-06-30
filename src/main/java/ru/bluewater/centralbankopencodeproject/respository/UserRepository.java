package ru.bluewater.centralbankopencodeproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bluewater.centralbankopencodeproject.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
