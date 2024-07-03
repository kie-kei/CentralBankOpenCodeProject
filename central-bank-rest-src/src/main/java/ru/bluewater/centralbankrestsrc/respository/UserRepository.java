package ru.bluewater.centralbankrestsrc.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.bluewater.centralbankrestsrc.entity.UserEntity;

import java.util.Optional;
@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
