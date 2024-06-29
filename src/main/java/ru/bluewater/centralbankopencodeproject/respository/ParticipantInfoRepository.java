package ru.bluewater.centralbankopencodeproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bluewater.centralbankopencodeproject.entity.ParticipantInfo;

import java.util.UUID;

public interface ParticipantInfoRepository extends JpaRepository<ParticipantInfo, UUID> {
}
