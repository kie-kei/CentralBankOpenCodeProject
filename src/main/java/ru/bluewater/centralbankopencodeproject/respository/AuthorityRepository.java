package ru.bluewater.centralbankopencodeproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bluewater.centralbankopencodeproject.entity.AuthorityEntity;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {
    Optional<AuthorityEntity> findByAuthority(String authority);
}
