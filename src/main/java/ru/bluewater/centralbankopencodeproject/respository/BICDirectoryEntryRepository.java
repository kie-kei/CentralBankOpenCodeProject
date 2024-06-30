package ru.bluewater.centralbankopencodeproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bluewater.centralbankopencodeproject.entity.BICDirectoryEntry;

import java.util.UUID;

public interface BICDirectoryEntryRepository extends JpaRepository<BICDirectoryEntry, UUID> {
}
