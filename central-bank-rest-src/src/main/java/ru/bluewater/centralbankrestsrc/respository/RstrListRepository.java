package ru.bluewater.centralbankrestsrc.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bluewater.centralbankrestsrc.entity.RstrListEntity;

import java.util.UUID;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(exported = false)
public interface RstrListRepository extends JpaRepository<RstrListEntity, UUID> {
}
