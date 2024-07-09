package ru.bluewater.centralbankrestsrc.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.bluewater.centralbankrestsrc.entity.ED807Entity;

import java.util.UUID;
@RepositoryRestResource(exported = false)
public interface ED807Repository extends JpaRepository<ED807Entity, UUID> {

//    @Query("update ed807 ed set ed.createdAt = :createdAt, ed.createdBy = :createdBy where ed.uuid = :uuid")
//    void createAuditFields(
//            @Param("uuid") UUID uuid,
//            @Param("createdAt") LocalDateTime createdAt,
//            @Param("createdBy") String createdBy
//    );
}
