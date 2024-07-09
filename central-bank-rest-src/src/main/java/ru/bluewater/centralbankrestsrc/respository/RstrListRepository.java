package ru.bluewater.centralbankrestsrc.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.bluewater.centralbankrestsrc.entity.RstrListEntity;

import java.util.UUID;
@RepositoryRestResource(exported = false)
public interface RstrListRepository extends JpaRepository<RstrListEntity, UUID> {
}
