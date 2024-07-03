package ru.bluewater.centralbankrestsrc.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.bluewater.centralbankrestsrc.entity.AuthorityEntity;

import java.util.Optional;
@RepositoryRestResource(exported = false)
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {
    Optional<AuthorityEntity> findByAuthority(String authority);
}
