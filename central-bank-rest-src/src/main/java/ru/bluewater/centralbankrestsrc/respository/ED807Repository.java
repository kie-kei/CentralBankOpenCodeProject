package ru.bluewater.centralbankrestsrc.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.entity.ED807Entity;

import java.util.UUID;
@RepositoryRestResource(exported = false)
public interface ED807Repository extends JpaRepository<ED807Entity, UUID> {

    @Query("SELECT b FROM ed807 b JOIN b.bicDirectoryEntry a WHERE a = :bicDirectoryEntry")
    ED807Entity findByBicDirectoryEntry(BICDirectoryEntryEntity bicDirectoryEntry);
}
