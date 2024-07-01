package ru.bluewater.centralbankopencodeproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bluewater.centralbankopencodeproject.entity.ParticipantInfoEntity;

import java.util.UUID;

public interface ParticipantInfoRepository extends JpaRepository<ParticipantInfoEntity, UUID> {
}
