package ru.bluewater.centralbankrestsrc.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.bluewater.centralbankrestsrc.entity.SWBICSEntity;

import java.util.UUID;
@RepositoryRestResource(exported = false)
public interface SWBICSRepository extends JpaRepository<SWBICSEntity, UUID> {
}
