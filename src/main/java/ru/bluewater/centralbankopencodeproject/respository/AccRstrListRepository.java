package ru.bluewater.centralbankopencodeproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bluewater.centralbankopencodeproject.entity.AccRstrListEntity;

import java.util.UUID;

public interface AccRstrListRepository extends JpaRepository<AccRstrListEntity, UUID> {
}
