package ru.bluewater.centralbankrestsrc.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bluewater.centralbankrestsrc.entity.PartInfoEntity;

import java.util.UUID;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(exported = false)
public interface PartInfoRepository extends JpaRepository<PartInfoEntity, UUID> {
}
