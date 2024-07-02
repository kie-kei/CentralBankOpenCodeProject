package ru.bluewater.centralbankopencodeproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bluewater.centralbankopencodeproject.entity.PartInfoEntity;

import java.util.UUID;

public interface PartInfoRepository extends JpaRepository<PartInfoEntity, UUID> {
}
