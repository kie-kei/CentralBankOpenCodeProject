package ru.bluewater.centralbankrestsrc.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.annotation.Validated;
import ru.bluewater.centralbankrestsrc.entity.RootEntity;

import java.util.UUID;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(exported = false)
public interface RootRepository extends JpaRepository<RootEntity, UUID> {
}
