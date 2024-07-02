package ru.bluewater.centralbankrestsrc.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;

import java.util.UUID;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(exported = false)
public interface BICDirectoryEntryRepository extends JpaRepository<BICDirectoryEntryEntity, UUID> {
}
