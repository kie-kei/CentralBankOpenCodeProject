package ru.bluewater.centralbankopencodeproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bluewater.centralbankopencodeproject.entity.SWBICSEntity;

import java.util.UUID;

public interface SWBICSRepository extends JpaRepository<SWBICSEntity, UUID> {
}
