package ru.bluewater.centralbankrestsrc.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.entity.ED807Entity;
import ru.bluewater.centralbankrestsrc.entity.ParticipantInfoEntity;
import ru.bluewater.centralbankrestsrc.entity.RstrListEntity;

import java.util.UUID;
@RepositoryRestResource(exported = false)
public interface ParticipantInfoRepository extends JpaRepository<ParticipantInfoEntity, UUID> {
    @Query("SELECT b FROM participant_info b JOIN b.rstrList a WHERE a = :rstrList")
    ParticipantInfoEntity findByRstrList(RstrListEntity rstrList);
}
