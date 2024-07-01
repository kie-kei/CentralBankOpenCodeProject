package ru.bluewater.centralbankopencodeproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bluewater.centralbankopencodeproject.entity.BICDirectoryEntryEntity;

import java.util.UUID;

public interface BICDirectoryEntryRepository extends JpaRepository<BICDirectoryEntryEntity, UUID> {
}
