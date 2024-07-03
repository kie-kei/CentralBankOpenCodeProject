package ru.bluewater.centralbankrestsrc.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.bluewater.centralbankrestsrc.entity.PartInfoEntity;

import java.util.UUID;
@RepositoryRestResource(exported = false)
public interface PartInfoRepository extends JpaRepository<PartInfoEntity, UUID> {
}
